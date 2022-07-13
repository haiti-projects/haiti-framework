package dev.struchkov.godfather.core;

import dev.struchkov.autoresponder.Responder;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.UnitActiveType;
import dev.struchkov.godfather.context.exception.ConfigAppException;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.Modifiable;
import dev.struchkov.godfather.context.service.PersonSettingService;
import dev.struchkov.godfather.context.service.StorylineService;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.core.service.ErrorHandler;
import dev.struchkov.godfather.core.service.action.ActionUnit;
import dev.struchkov.godfather.core.service.action.AnswerCheckAction;
import dev.struchkov.godfather.core.service.action.AnswerSaveAction;
import dev.struchkov.godfather.core.service.action.AnswerTextAction;
import dev.struchkov.godfather.core.service.action.AnswerTimerAction;
import dev.struchkov.godfather.core.service.action.AnswerValidityAction;
import dev.struchkov.godfather.core.service.timer.TimerService;
import dev.struchkov.haiti.context.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralAutoResponder<T extends Message> {

    private final PersonSettingService personSettingService;
    private final StorylineService<T> storyLineService;
    protected Map<String, ActionUnit<? extends MainUnit, ? extends Message>> actionUnitMap = new HashMap<>();
    protected List<Modifiable<T>> modifiable;
    private ErrorHandler errorHandler;

    protected GeneralAutoResponder(
            Sending sending,
            PersonSettingService personSettingService,
            StorylineService<T> storyLineService
    ) {
        this.personSettingService = personSettingService;
        this.storyLineService = storyLineService;
        init(sending);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
    }

    public void initModifiable(List<Modifiable<T>> modifiable) {
        this.modifiable = modifiable;
    }

    public void initActionUnit(String typeUnit, ActionUnit<? extends MainUnit, T> actionUnit) {
        if (!actionUnitMap.containsKey(typeUnit)) {
            actionUnitMap.put(typeUnit, actionUnit);
        } else {
            throw new ConfigAppException("Обработка такого типа юнита уже зарегистрирована");
        }
    }

    public void initSaveAction(AnswerSaveAction<?> answerSaveAction) {
        actionUnitMap.put(TypeUnit.SAVE, answerSaveAction);
    }

    public void initTimerAction(TimerService timerService) {
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(timerService, this));
    }

    /**
     * Позволяет установить перехватчик и обработчик исключений, возникающих при обработке юнитов.
     */
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void processingNewMessage(T newMessage) {
        if (newMessage != null) {
            final boolean state = personSettingService.getStateProcessingByPersonId(newMessage.getPersonId()).orElse(true);
            if (state) {
                processing(newMessage);
            }
        }
    }

    public void processingNewMessages(List<T> newMessages) {
        if (newMessages != null && !newMessages.isEmpty()) {
            final Set<Long> personIds = newMessages.stream()
                    .map(Message::getPersonId)
                    .collect(Collectors.toSet());
            final Set<Long> disableIds = personSettingService.getAllPersonIdDisableMessages(personIds);
            final List<T> allowedMessages = newMessages.stream()
                    .filter(message -> !disableIds.contains(message.getPersonId()))
                    .toList();
            allowedMessages.parallelStream().forEach(this::processing);
        }
    }

    private void processing(T message) {
        if (modifiable != null) {
            modifiable.forEach(m -> m.change(message));
        }
        final Set<MainUnit> units = storyLineService.getNextUnitByPersonId(message.getPersonId());
        final Optional<MainUnit> optAnswer = Responder.nextUnit(message.getText(), units);
        if (optAnswer.isPresent()) {
            final MainUnit answer = optAnswer.get();
            if (checkPermission(answer.getAccessibility(), message)) {
                answer(UnitRequest.of(answer, message));
            }
        }
    }

    private boolean checkPermission(Optional<Accessibility> accessibility, T message) {
        return accessibility.isEmpty() || accessibility.get().check(message);
    }

    public void answer(UnitRequest<MainUnit, T> unitRequest) {
        try {
            unitRequest = getAction(unitRequest);
            unitRequest = activeUnitAfter(unitRequest);

            final Optional<MainUnit> optDefaultUnit = storyLineService.getDefaultUnit();
            final MainUnit unit = unitRequest.getUnit();
            final T message = unitRequest.getMessage();
            if (optDefaultUnit.isEmpty() || !optDefaultUnit.get().equals(unit)) {
                storyLineService.save(message.getPersonId(), unit.getName(), message);
            }
        } catch (Exception e) {
            if (errorHandler != null) {
                errorHandler.handle(unitRequest.getMessage(), e);
            } else {
                throw e;
            }
        }
    }

    private UnitRequest<MainUnit, T> activeUnitAfter(UnitRequest<MainUnit, T> unitRequest) {
        final Set<MainUnit> nextUnits = unitRequest.getUnit().getNextUnits();
        if (nextUnits != null) {
            Optional<MainUnit> first = nextUnits.stream()
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
                    .findFirst();
            if (first.isPresent()) {
                getAction(UnitRequest.of(first.get(), unitRequest.getMessage()));
                return activeUnitAfter(UnitRequest.of(first.get(), unitRequest.getMessage()));
            }
        }
        return unitRequest;
    }

    private UnitRequest<MainUnit, T> getAction(UnitRequest<MainUnit, T> unitRequest) {
        final MainUnit unit = unitRequest.getUnit();
        final String typeUnit = unit.getType();
        if (actionUnitMap.containsKey(typeUnit)) {
            ActionUnit actionUnit = actionUnitMap.get(typeUnit);
            UnitRequest<MainUnit, T> newUnitRequest = actionUnit.action(unitRequest);
            final MainUnit newUnit = newUnitRequest.getUnit();
            return !unit.equals(newUnit) ? getAction(newUnitRequest) : unitRequest;
        } else {
            throw new NotFoundException("ActionUnit для типа {0} не зарегистрирован", unit.getType());
        }
    }

}
