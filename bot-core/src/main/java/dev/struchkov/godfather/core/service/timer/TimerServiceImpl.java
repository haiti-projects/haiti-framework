package dev.struchkov.godfather.core.service.timer;

import dev.struchkov.godfather.core.domain.Timer;
import dev.struchkov.godfather.core.repository.TimerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimerServiceImpl implements TimerService {

    private static final Logger log = LoggerFactory.getLogger(TimerServiceImpl.class);

    private final TimerRepository timerRepository;

    public TimerServiceImpl(TimerRepository timerRepository) {
        this.timerRepository = timerRepository;
    }

    public TimerRepository getTimerRepository() {
        return timerRepository;
    }

    @Override
    public List<Timer> getTimerActive() {
        return new ArrayList<>(timerRepository.getTimerActive(LocalDateTime.now()));
    }

    public Integer add(Timer timer) {
        log.info("Таймер установлен: {}", timer);
        return timerRepository.add(timer);
    }

    public void remove(Integer id) {
        log.info("Таймер удален");
        timerRepository.remove(id);
    }

    public void edit(Integer id, Timer timer) {
        timer.setId(id);
        timerRepository.edit(timer);
    }

}
