package dev.struchkov.godfather.core;

import dev.struchkov.autoresponder.Responder;
import dev.struchkov.autoresponder.entity.Unit;
import dev.struchkov.godfather.context.domain.UnitPointer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.ConfigAppException;
import dev.struchkov.godfather.context.service.Modifiable;
import dev.struchkov.godfather.context.service.PersonSettingService;
import dev.struchkov.godfather.context.service.UnitPointerService;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import dev.struchkov.godfather.core.domain.unit.UnitActiveType;
import dev.struchkov.godfather.core.service.Accessibility;
import dev.struchkov.godfather.core.service.ErrorHandler;
import dev.struchkov.godfather.core.service.action.ActionUnit;
import dev.struchkov.godfather.core.service.action.AnswerCheckAction;
import dev.struchkov.godfather.core.service.action.AnswerProcessingAction;
import dev.struchkov.godfather.core.service.action.AnswerSaveAction;
import dev.struchkov.godfather.core.service.action.AnswerTextAction;
import dev.struchkov.godfather.core.service.action.AnswerTimerAction;
import dev.struchkov.godfather.core.service.action.AnswerValidityAction;
import dev.struchkov.godfather.core.service.timer.TimerService;
import dev.struchkov.godfather.core.utils.TypeUnit;
import dev.struchkov.haiti.context.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralAutoResponder<T extends Message> {

    private ErrorHandler errorHandler;
    private final PersonSettingService personSettingService;
    private final UnitPointerService unitPointerService;
    private final StoryLine storyLine;

    protected Map<String, ActionUnit<? extends MainUnit, ? extends Message>> actionUnitMap = new HashMap<>();
    protected List<Modifiable<T>> modifiable;

    protected GeneralAutoResponder(
            Sending sending,
            PersonSettingService personSettingService,
            UnitPointerService unitPointerService,
            List<Object> unitConfigurations
    ) {
        this.personSettingService = personSettingService;
        this.unitPointerService = unitPointerService;
        this.storyLine = new StorylineMaker(unitConfigurations).createStoryLine();
        init(sending);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sending));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
    }

    public void initModifiable(List<Modifiable<T>> modifiable) {
        this.modifiable = modifiable;
    }

    public void initActionUnit(String typeUnit, ActionUnit<? super MainUnit, T> actionUnit) {
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
        final Set<MainUnit> units = unitPointerService.getUnitNameByPersonId(message.getPersonId())
                .flatMap(storyLine::getUnit)
                .map(Unit::getNextUnits)
                .filter(mainUnits -> !mainUnits.isEmpty())
                .orElse(storyLine.getStartingUnits());
        final Optional<MainUnit> optAnswer = Responder.nextUnit(message.getText(), units);
        if (optAnswer.isPresent()) {
            final MainUnit answer = optAnswer.get();
            if (checkPermission(answer.getAccessibility(), message)) {
                answer(message, answer);
            }
        }
    }

    private boolean checkPermission(Optional<Accessibility> accessibility, T message) {
        return accessibility.isEmpty() || accessibility.get().check(message);
    }

    public void answer(T message, MainUnit unitAnswer) {
        try {
            unitAnswer = getAction(message, unitAnswer);
            unitAnswer = activeUnitAfter(unitAnswer, message);

            final Optional<MainUnit> optDefaultUnit = storyLine.getDefaultUnit();
            if (optDefaultUnit.isEmpty() || !optDefaultUnit.get().equals(unitAnswer)) {
                unitPointerService.save(new UnitPointer(message.getPersonId(), unitAnswer.getName()));
            }
        } catch (Exception e) {
            if (errorHandler != null) {
                errorHandler.handle(message, e);
            } else {
                throw e;
            }
        }
    }

    private MainUnit activeUnitAfter(MainUnit mainUnit, T content) {
        if (mainUnit.getNextUnits() != null) {
            Optional<MainUnit> first = mainUnit.getNextUnits().stream()
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
                    .findFirst();
            if (first.isPresent()) {
                getAction(content, first.get());
                return activeUnitAfter(first.get(), content);
            }
        }
        return mainUnit;
    }

    private MainUnit getAction(T event, MainUnit unitAnswer) {
        if (actionUnitMap.containsKey(unitAnswer.getType())) {
            ActionUnit actionUnit = actionUnitMap.get(unitAnswer.getType());
            MainUnit mainUnit = actionUnit.action(unitAnswer, event);
            return !unitAnswer.equals(mainUnit) ? getAction(event, mainUnit) : mainUnit;
        } else {
            throw new NotFoundException("ActionUnit для типа {0} не зарегистрирован", unitAnswer.getType());
        }
    }

    //TODO [22.06.2022]: Временное решение для ленивой инициализации
    public void link(String firstName, String secondName) {
        storyLine.link(firstName, secondName);
    }

}
