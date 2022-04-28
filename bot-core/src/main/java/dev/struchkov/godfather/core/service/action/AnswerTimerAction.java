package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.TimerSettingException;
import dev.struchkov.godfather.core.GeneralAutoResponder;
import dev.struchkov.godfather.core.domain.Timer;
import dev.struchkov.godfather.core.domain.unit.AnswerTimer;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
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
    public MainUnit action(AnswerTimer answerTimer, Message message) {
        LocalDateTime timeActive = LocalDateTime
                .now(Clock.tickSeconds(ZoneId.systemDefault()))
                .plusSeconds(Optional
                        .ofNullable(answerTimer.getTimeDelaySec())
                        .orElseThrow(() -> new TimerSettingException("Не установлена временная задержка таймера")));

        Timer.Builder timer = Timer.builder()
                .personId(message.getPersonId())
                .unitAnswer(answerTimer.getUnitAnswer())
                .timeActive(timeActive)
                .periodSec(answerTimer.getTimeDelaySec())
                .checkLoop(answerTimer.getCheckLoop());

        if (answerTimer.getTimeDeathSec() != null) {
            timer.timeDeath(LocalDateTime
                    .now(Clock.tickSeconds(ZoneId.systemDefault()))
                    .plusSeconds(answerTimer.getTimeDeathSec()));
        } else if (answerTimer.getCheckLoop() == null) {
            timer.timeDeath(timeActive);
        }

        timerService.add(timer.build());
        return answerTimer;
    }
}
