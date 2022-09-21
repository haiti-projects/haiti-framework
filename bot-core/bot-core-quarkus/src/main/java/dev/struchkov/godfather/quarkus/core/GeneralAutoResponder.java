package dev.struchkov.godfather.quarkus.core;

import dev.struchkov.autoresponder.Responder;
import dev.struchkov.godfather.exception.ConfigAppException;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.ErrorHandler;
import dev.struchkov.godfather.quarkus.context.service.Modifiable;
import dev.struchkov.godfather.quarkus.context.service.PersonSettingService;
import dev.struchkov.godfather.quarkus.context.service.Sending;
import dev.struchkov.godfather.quarkus.core.action.ActionUnit;
import dev.struchkov.godfather.quarkus.core.action.AnswerCheckAction;
import dev.struchkov.godfather.quarkus.core.action.AnswerSaveAction;
import dev.struchkov.godfather.quarkus.core.action.AnswerTextAction;
import dev.struchkov.godfather.quarkus.core.action.cmd.ReplaceCmdAction;
import dev.struchkov.godfather.quarkus.core.service.StorylineService;
import dev.struchkov.godfather.quarkus.core.unit.MainUnit;
import dev.struchkov.godfather.quarkus.core.unit.UnitRequest;
import dev.struchkov.haiti.context.exception.NotFoundException;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Checker.checkNotEmpty;
import static dev.struchkov.haiti.utils.Checker.checkNotNull;
import static dev.struchkov.haiti.utils.Checker.checkNull;

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

    public Uni<Void> processingNewMessage(M newMessage) {
        if (newMessage != null) {
            return personSettingService.getStateProcessingByPersonId(newMessage.getPersonId())
                    .onItem().transformToUni(
                            state -> {
                                if (checkNull(state) || state) {
                                    return processing(newMessage);
                                }
                                return Uni.createFrom().voidItem();
                            }
                    );
        }
        return Uni.createFrom().voidItem();
    }

    public Uni<Void> processingNewMessages(List<M> newMessages) {
        if (newMessages != null && !newMessages.isEmpty()) {
            final Set<Long> personIds = newMessages.stream()
                    .map(Message::getPersonId)
                    .collect(Collectors.toSet());
            return personSettingService.getAllPersonIdDisableMessages(personIds)
                    .onItem().transformToMulti(
                            disableIds -> {
                                final List<M> allowedMessages = newMessages.stream()
                                        .filter(message -> !disableIds.contains(message.getPersonId()))
                                        .toList();
                                return Multi.createFrom().iterable(allowedMessages);
                            }
                    )
                    .onItem().transform(this::processing)
                    .toUni().replaceWithVoid();
        }
        return Uni.createFrom().voidItem();
    }

    private Uni<Void> processing(M message) {
        return Uni.createFrom().item(message)
                .onItem().transformToUni(
                        mail -> {
                            if (checkNotEmpty(modifiable)) {
                                return Multi.createFrom().iterable(modifiable)
                                        .onItem().transformToUni(m -> m.change(mail))
                                        .concatenate().toUni().replaceWith(mail);
                            }
                            return Uni.createFrom().item(mail);
                        }
                ).onItem().transformToUni(
                        mail -> {
                            final Uni<Set<MainUnit<M>>> uniUnits = storyLineService.getNextUnitByPersonId(mail.getPersonId());
                            return Uni.combine().all().unis(uniUnits, Uni.createFrom().item(mail)).asTuple();
                        }
                ).onItem().transformToUni(
                        t -> {
                            final Set<MainUnit<M>> units = t.getItem1();
                            final M mail = t.getItem2();
                            final Optional<MainUnit<M>> optAnswer = Responder.nextUnit(mail, units).or(storyLineService::getDefaultUnit);
                            if (optAnswer.isPresent()) {
                                final MainUnit<M> answer = optAnswer.get();
                                //TODO [05.08.2022]: нужно ли проверку встраивать в поток?
//                                if (checkPermission(answer.getAccessibility(), message)) {
                                return answer(UnitRequest.of(answer, message));
//                                }
                            }
                            return Uni.createFrom().voidItem();
                        }
                );
    }

//    private boolean checkPermission(Optional<Accessibility> accessibility, M message) {
//        return accessibility.isEmpty() || accessibility.get().check(message);
//    }

    public Uni<Void> answer(UnitRequest<MainUnit, M> unitRequest) {
        return getAction(unitRequest)
                .onItem().transformToUni(
                        request -> activeUnitAfter(unitRequest)
                )
                .onFailure().invoke(
                        throwable -> {
                            if (errorHandler != null) {
                                errorHandler.handle(unitRequest.getMessage(), throwable);
                            }
                        }
                )
                .replaceWithVoid();
    }

    private Uni<UnitRequest<MainUnit, M>> activeUnitAfter(UnitRequest<MainUnit, M> unitRequest) {
        final Set<MainUnit<M>> nextUnits = unitRequest.getUnit().getNextUnits();
        if (checkNotNull(nextUnits)) {
            Optional<MainUnit<M>> first = nextUnits.stream()
                    .filter(unit -> UnitActiveType.AFTER.equals(unit.getActiveType()))
                    .findFirst();
            if (first.isPresent()) {
                return Uni.createFrom().voidItem().onItem().transformToUni(
                                v-> getAction(UnitRequest.of(first.get(), unitRequest.getMessage()))
                        )
                        .onItem().transformToUni(
                                uR -> activeUnitAfter(UnitRequest.of(first.get(), unitRequest.getMessage()))
                        );
            }
        }
        return Uni.createFrom().item(unitRequest);
    }

    private Uni<UnitRequest<MainUnit, M>> getAction(UnitRequest<MainUnit, M> unitRequest) {
        final MainUnit<M> unit = unitRequest.getUnit();
        final M message = unitRequest.getMessage();
        final String typeUnit = unit.getType();
        if (actionUnitMap.containsKey(typeUnit)) {
            ActionUnit<MainUnit, M> actionUnit = actionUnitMap.get(typeUnit);
            return actionUnit.action(unitRequest)
                    .onItem().transformToUni(
                            newUnitRequest -> {
                                final Optional<MainUnit<M>> optDefaultUnit = storyLineService.getDefaultUnit();
                                if (!unit.isNotSaveHistory() && (optDefaultUnit.isEmpty() || !optDefaultUnit.get().equals(unit))) {
                                    return Uni.combine().all().unis(
                                            Uni.createFrom().item(newUnitRequest),
                                            storyLineService.save(message.getPersonId(), unit.getName(), message)
                                    ).asTuple();
                                }
                                return Uni.combine().all().unis(Uni.createFrom().item(newUnitRequest), Uni.createFrom().voidItem()).asTuple();
                            }
                    ).onItem().transformToUni(
                            t -> {
                                final UnitRequest<MainUnit, M> newUnitRequest = t.getItem1();
                                final MainUnit<M> newUnit = newUnitRequest.getUnit();
                                return !unit.equals(newUnit) ? getAction(newUnitRequest) : Uni.createFrom().item(unitRequest);
                            }
                    );
        } else {
            throw new NotFoundException("ActionUnit для типа {0} не зарегистрирован", unit.getType());
        }
    }

}
