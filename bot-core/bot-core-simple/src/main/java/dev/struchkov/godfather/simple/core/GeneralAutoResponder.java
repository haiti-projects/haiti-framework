package dev.struchkov.godfather.simple.core;

import dev.struchkov.autoresponder.Responder;
import dev.struchkov.godfather.exception.ConfigAppException;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.context.service.Accessibility;
import dev.struchkov.godfather.simple.context.service.ErrorHandler;
import dev.struchkov.godfather.simple.context.service.Modifiable;
import dev.struchkov.godfather.simple.context.service.PersonSettingService;
import dev.struchkov.godfather.simple.context.service.Sending;
import dev.struchkov.godfather.simple.core.action.ActionUnit;
import dev.struchkov.godfather.simple.core.action.AnswerCheckAction;
import dev.struchkov.godfather.simple.core.action.AnswerSaveAction;
import dev.struchkov.godfather.simple.core.action.AnswerTextAction;
import dev.struchkov.godfather.simple.core.action.cmd.ReplaceCmdAction;
import dev.struchkov.godfather.simple.core.service.StorylineService;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.godfather.simple.core.unit.UnitRequest;
import dev.struchkov.haiti.context.exception.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralAutoResponder<M extends Message> {

    private final PersonSettingService personSettingService;
    private final StorylineService<M> storyLineService;
    protected Map<String, ActionUnit> actionUnitMap = new HashMap<>();
    protected List<Modifiable<M>> modifiable;
    private ErrorHandler errorHandler;

    protected GeneralAutoResponder(
            Sending sending,
            PersonSettingService personSettingService,
            StorylineService<M> storyLineService
    ) {
        this.personSettingService = personSettingService;
        this.storyLineService = storyLineService;
        init(sending);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction<>());
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.REPLACE_CMD, new ReplaceCmdAction());
    }

    public void initModifiable(List<Modifiable<M>> modifiable) {
        this.modifiable = modifiable;
    }

    public void initActionUnit(String typeUnit, ActionUnit<? extends MainUnit<M>, M> actionUnit) {
        if (!actionUnitMap.containsKey(typeUnit)) {
            actionUnitMap.put(typeUnit, actionUnit);
        } else {
            throw new ConfigAppException("Обработка такого типа юнита уже зарегистрирована");
        }
    }

    public void initSaveAction(AnswerSaveAction<M, ?> answerSaveAction) {
        actionUnitMap.put(TypeUnit.SAVE, answerSaveAction);
    }

    /**
     * Позволяет установить перехватчик и обработчик исключений, возникающих при обработке юнитов.
     */
    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    public void setDefaultUnit(String unitName) {
        storyLineService.setDefaultUnit(unitName);
    }

    public void processingNewMessage(M newMessage) {
        if (newMessage != null) {
            final boolean state = personSettingService.getStateProcessingByPersonId(newMessage.getPersonId()).orElse(true);
            if (state) {
                processing(newMessage);
            }
        }
    }

    public void processingNewMessages(List<M> newMessages) {
        if (newMessages != null && !newMessages.isEmpty()) {
            final Set<String> personIds = newMessages.stream()
                    .map(Message::getPersonId)
                    .collect(Collectors.toSet());
            final Set<String> disableIds = personSettingService.getAllPersonIdDisableMessages(personIds);
            final List<M> allowedMessages = newMessages.stream()
                    .filter(message -> !disableIds.contains(message.getPersonId()))
                    .toList();
            allowedMessages.parallelStream().forEach(this::processing);
        }
    }

    private void processing(M message) {
        if (modifiable != null) {
            modifiable.forEach(m -> m.change(message));
        }
        final Set<MainUnit<M>> units = storyLineService.getNextUnitByPersonId(message.getPersonId());
        final Optional<MainUnit<M>> optAnswer = Responder.nextUnit(message, units)
                .or(storyLineService::getDefaultUnit);
        if (optAnswer.isPresent()) {
            final MainUnit<M> answer = optAnswer.get();
            if (checkPermission(answer.getAccessibility(), message)) {
                answer(UnitRequest.of(answer, message));
            }
        }
    }

    private boolean checkPermission(Optional<Accessibility> accessibility, M message) {
        return accessibility.isEmpty() || accessibility.get().check(message);
    }

    public void answer(UnitRequest<MainUnit<M>, M> unitRequest) {
        try {
            unitRequest = getAction(unitRequest);
            activeUnitAfter(unitRequest);
        } catch (Exception e) {
            if (errorHandler != null) {
                errorHandler.handle(unitRequest.getMessage(), e);
            } else {
                throw e;
            }
        }
    }

    private UnitRequest<MainUnit<M>, M> activeUnitAfter(UnitRequest<MainUnit<M>, M> unitRequest) {
        final Set<MainUnit<M>> nextUnits = unitRequest.getUnit().getNextUnits();
        if (nextUnits != null) {
            Optional<MainUnit<M>> first = nextUnits.stream()
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
                    .findFirst();
            if (first.isPresent()) {
                getAction(UnitRequest.of(first.get(), unitRequest.getMessage()));
                return activeUnitAfter(UnitRequest.of(first.get(), unitRequest.getMessage()));
            }
        }
        return unitRequest;
    }

    private UnitRequest<MainUnit<M>, M> getAction(UnitRequest<MainUnit<M>, M> unitRequest) {
        final MainUnit<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();
        final String typeUnit = unit.getType();
        if (actionUnitMap.containsKey(typeUnit)) {
            ActionUnit actionUnit = actionUnitMap.get(typeUnit);
            UnitRequest<MainUnit<M>, M> newUnitRequest = actionUnit.action(unitRequest);
            final Optional<MainUnit<M>> optDefaultUnit = storyLineService.getDefaultUnit();
            if (!unit.isNotSaveHistory() && (optDefaultUnit.isEmpty() || !optDefaultUnit.get().equals(unit))) {
                storyLineService.save(message.getPersonId(), unit.getName(), message);
            }
            final MainUnit<M> newUnit = newUnitRequest.getUnit();
            return !unit.equals(newUnit) ? getAction(newUnitRequest) : unitRequest;
        } else {
            throw new NotFoundException("ActionUnit для типа {0} не зарегистрирован", unit.getType());
        }
    }

}
