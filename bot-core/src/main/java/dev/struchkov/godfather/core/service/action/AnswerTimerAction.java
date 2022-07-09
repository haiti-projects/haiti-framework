package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.TimerSettingException;
import dev.struchkov.godfather.core.GeneralAutoResponder;
import dev.struchkov.godfather.core.domain.Timer;
import dev.struchkov.godfather.context.domain.unit.AnswerTimer;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.core.service.timer.TimerActionTask;
import dev.struchkov.godfather.core.service.timer.TimerService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

/**
 * Обработчик Unit-а {@link AnswerTimer}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerTimerAction implements ActionUnit<AnswerTimer, Message> {

    private TimerService timerService;
    private Long verificationPeriodSec = 15L;

    public AnswerTimerAction(TimerService timerService, GeneralAutoResponder generalAutoresponder) {
        this.timerService = timerService;
        TimerActionTask timerActionTask = new TimerActionTask(timerService, generalAutoresponder);
        java.util.Timer timer = new java.util.Timer(true);
        timer.schedule(timerActionTask, 0, 1000L * verificationPeriodSec);
    }

    public Long getVerificationPeriodSec() {
        return verificationPeriodSec;
    }

    public void setVerificationPeriodSec(Long verificationPeriodSec) {
        this.verificationPeriodSec = verificationPeriodSec;
    }

    @Override
    public UnitRequest<MainUnit, Message> action(UnitRequest<AnswerTimer, Message> unitRequest) {
        final AnswerTimer unit = unitRequest.getUnit();
        final Message message = unitRequest.getMessage();

        final LocalDateTime timeActive = LocalDateTime
                .now(Clock.tickSeconds(ZoneId.systemDefault()))
                .plusSeconds(Optional
                        .ofNullable(unit.getTimeDelaySec())
                        .orElseThrow(() -> new TimerSettingException("Не установлена временная задержка таймера")));

        final Timer.Builder timer = Timer.builder()
                .personId(message.getPersonId())
                .unitAnswer(unit.getUnitAnswer())
                .timeActive(timeActive)
                .periodSec(unit.getTimeDelaySec())
                .checkLoop(unit.getCheckLoop());

        if (unit.getTimeDeathSec() != null) {
            timer.timeDeath(LocalDateTime
                    .now(Clock.tickSeconds(ZoneId.systemDefault()))
                    .plusSeconds(unit.getTimeDeathSec()));
        } else if (unit.getCheckLoop() == null) {
            timer.timeDeath(timeActive);
        }

        timerService.add(timer.build());
        return UnitRequest.of(unit, message);
    }
}
