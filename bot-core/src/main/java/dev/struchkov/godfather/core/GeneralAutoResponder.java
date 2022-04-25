package dev.struchkov.godfather.core;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.ConfigAppException;
import dev.struchkov.godfather.context.exception.NotFoundException;
import dev.struchkov.godfather.context.service.MessageService;
import dev.struchkov.godfather.context.service.Modifiable;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import dev.struchkov.godfather.core.domain.unit.UnitActiveType;
import dev.struchkov.godfather.core.service.action.ActionUnit;
import dev.struchkov.godfather.core.service.action.AnswerCheckAction;
import dev.struchkov.godfather.core.service.action.AnswerProcessingAction;
import dev.struchkov.godfather.core.service.action.AnswerSaveAction;
import dev.struchkov.godfather.core.service.action.AnswerTextAction;
import dev.struchkov.godfather.core.service.action.AnswerTimerAction;
import dev.struchkov.godfather.core.service.action.AnswerValidityAction;
import dev.struchkov.godfather.core.service.timer.TimerService;
import dev.struchkov.godfather.core.utils.TypeUnit;
import org.sadtech.autoresponder.AutoResponder;
import org.sadtech.autoresponder.entity.UnitPointer;
import org.sadtech.autoresponder.repository.UnitPointerRepository;
import org.sadtech.autoresponder.service.UnitPointerServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class GeneralAutoResponder<T extends Message> extends TimerTask {

    protected final AutoResponder<MainUnit> autoResponder;
    private final MessageService<T> messageService;
    protected Map<String, ActionUnit<? extends MainUnit, ? extends Message>> actionUnitMap = new HashMap<>();
    protected List<Modifiable<T>> modifiables;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    protected GeneralAutoResponder(Set<MainUnit> menuUnit,
                                   Sending sending,
                                   MessageService<T> messageService,
                                   UnitPointerRepository<MainUnit> unitPointerRepository
    ) {
        this.messageService = messageService;
        autoResponder = new AutoResponder<>(new UnitPointerServiceImpl<>(unitPointerRepository), menuUnit);
        init(sending);
    }

    private void init(Sending sending) {
        actionUnitMap.put(TypeUnit.CHECK, new AnswerCheckAction());
        actionUnitMap.put(TypeUnit.PROCESSING, new AnswerProcessingAction(sending));
        actionUnitMap.put(TypeUnit.TEXT, new AnswerTextAction(sending));
        actionUnitMap.put(TypeUnit.VALIDITY, new AnswerValidityAction());
    }

    public void initModifiables(List<Modifiable<T>> modifiables) {
        this.modifiables = modifiables;
    }

    protected void initActionUnit(String typeUnit, ActionUnit<? super MainUnit, T> actionUnit) {
        if (!actionUnitMap.containsKey(typeUnit)) {
            actionUnitMap.put(typeUnit, actionUnit);
        } else {
            throw new ConfigAppException("Обработка такого типа юнита уже зарегистрирована");
        }
    }

    public <U extends MainUnit> void initDefaultUnit(U defaultUnit) {
        autoResponder.setDefaultUnit(defaultUnit);
    }

    public void initSaveAction(AnswerSaveAction<?> answerSaveAction) {
        actionUnitMap.put(TypeUnit.SAVE, answerSaveAction);
    }

    public void initTimerAction(TimerService timerService) {
        actionUnitMap.put(TypeUnit.TIMER, new AnswerTimerAction(timerService, this));
    }

    public void setDefaultUnit(MainUnit mainUnit) {
        autoResponder.setDefaultUnit(mainUnit);
    }

    public void checkNewMessage() {
        List<T> eventByTime = messageService.getNewMessage();
        if (eventByTime != null && !eventByTime.isEmpty()) {
            executorService.execute(
                    () -> eventByTime.parallelStream().forEach(processing())
            );
        }
    }

    private Consumer<T> processing() {
        return event -> {
            if (modifiables != null) {
                modifiables.forEach(modifiable -> modifiable.change(event));
            }
            autoResponder.answer(event.getPersonId(), event.getText()).ifPresent(unitAnswer -> answer(event, unitAnswer));
        };
    }

    public void answer(T event, MainUnit unitAnswer) {
        unitAnswer = getAction(event, unitAnswer);
        unitAnswer = activeUnitAfter(unitAnswer, event);
        if (!(autoResponder.getDefaultUnit() != null && autoResponder.getDefaultUnit().equals(unitAnswer))) {
            autoResponder.getUnitPointerService().save(new UnitPointer<>(event.getPersonId(), unitAnswer));
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
            throw new NotFoundException("ActionUnit для типа " + unitAnswer.getType() + " не зарегистрирован");
        }
    }

    @Override
    public void run() {
        checkNewMessage();
    }

}
