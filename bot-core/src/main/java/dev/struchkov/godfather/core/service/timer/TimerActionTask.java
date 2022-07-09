package dev.struchkov.godfather.core.service.timer;

import dev.struchkov.godfather.context.domain.UnitRequest;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.usercode.CheckData;
import dev.struchkov.godfather.context.utils.MessageUtils;
import dev.struchkov.godfather.core.GeneralAutoResponder;
import dev.struchkov.godfather.core.domain.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

/**
 * Обслуживание таймеров. Отвечает за активацию, удаление, перенастройку таймеров.
 *
 * @author upagge [11/07/2019]
 */
public class TimerActionTask extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(TimerActionTask.class);

    private final TimerService timerService;
    private final GeneralAutoResponder generalAutoresponder;

    public TimerActionTask(TimerService timerService, GeneralAutoResponder generalAutoresponder) {
        this.generalAutoresponder = generalAutoresponder;
        log.info("Инициализация сервиса по активации таймеров");
        this.timerService = timerService;
    }

    @Override
    public void run() {
        LocalDateTime nowDate = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));
        log.info("Сервис таймеров сработал. Время: {}", nowDate);

        timerService.getTimerActive()
                .parallelStream()
                .forEach(timer -> processingTimer(timer, nowDate));

    }

    private void processingTimer(Timer timer, LocalDateTime nowDate) {
        Message emptyMessage = MessageUtils.EMPTY_MESSAGE;
        emptyMessage.setPersonId(timer.getPersonId());
        CheckData checkLoop = timer.getCheckLoop();

        if (!timeDeath(nowDate, timer.getTimeDeath())) {
            if (checkLoop != null) {
                if (checkLoop.checked(emptyMessage)) {
                    generalAutoresponder.answer(UnitRequest.of(timer.getUnitAnswer(), emptyMessage));
                    timerService.remove(timer.getId());
                } else {
                    reinstallation(timer);
                }
            } else {
                generalAutoresponder.answer(UnitRequest.of(timer.getUnitAnswer(), emptyMessage));
                reinstallation(timer);
            }
        } else {
            generalAutoresponder.answer(UnitRequest.of(timer.getUnitAnswer(), emptyMessage));
            death(timer, emptyMessage);
        }
    }

    private void reinstallation(Timer timer) {
        if (timer.getPeriodSec() != null) {
            timer.setTimeActive(timer.getTimeActive().plusSeconds(timer.getPeriodSec()));
            timerService.edit(timer.getId(), timer);
        }
    }

    private void death(Timer timer, Message emptyMessage) {
        if (timer.getUnitDeath() != null) {
            generalAutoresponder.answer(UnitRequest.of(timer.getUnitDeath(), emptyMessage));
        }
        timerService.remove(timer.getId());
    }

    private boolean timeDeath(LocalDateTime nowTime, LocalDateTime timeDeath) {
        return timeDeath != null && nowTime.isAfter(timeDeath);
    }

}
