package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.service.usercode.CheckData;
import dev.struchkov.godfather.context.domain.unit.MainUnit;

import java.time.LocalDateTime;

/**
 * Используется для отложенной активации обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
public class Timer {

    /**
     * Идентификатор таймера.
     */
    private Integer id;

    /**
     * Unit, обработка которого откладывается.
     */
    private MainUnit unitAnswer;

    /**
     * Unit, который будет обработан после удаления таймера.
     */
    private MainUnit unitDeath;

    /**
     * Идентификатор пользователя.
     */
    private Long personId;

    /**
     * Время активации таймера.
     */
    private LocalDateTime timeActive;

    /**
     * Время смерти таймера.
     */
    private LocalDateTime timeDeath;

    /**
     * Интервал срабатывания таймера.
     */
    private Integer periodSec;

    /**
     * Условие срабатывания таймера.
     */
    private CheckData checkLoop;

    private Timer(Builder builder) {
        id = builder.id;
        unitAnswer = builder.unitAnswer;
        unitDeath = builder.unitDeath;
        personId = builder.personId;
        timeActive = builder.timeActive;
        timeDeath = builder.timeDeath;
        periodSec = builder.periodSec;
        checkLoop = builder.checkLoop;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MainUnit getUnitAnswer() {
        return unitAnswer;
    }

    public void setUnitAnswer(MainUnit unitAnswer) {
        this.unitAnswer = unitAnswer;
    }

    public MainUnit getUnitDeath() {
        return unitDeath;
    }

    public void setUnitDeath(MainUnit unitDeath) {
        this.unitDeath = unitDeath;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public LocalDateTime getTimeActive() {
        return timeActive;
    }

    public void setTimeActive(LocalDateTime timeActive) {
        this.timeActive = timeActive;
    }

    public LocalDateTime getTimeDeath() {
        return timeDeath;
    }

    public void setTimeDeath(LocalDateTime timeDeath) {
        this.timeDeath = timeDeath;
    }

    public Integer getPeriodSec() {
        return periodSec;
    }

    public void setPeriodSec(Integer periodSec) {
        this.periodSec = periodSec;
    }

    public CheckData getCheckLoop() {
        return checkLoop;
    }

    public void setCheckLoop(CheckData checkLoop) {
        this.checkLoop = checkLoop;
    }

    public static final class Builder {
        private Integer id;
        private MainUnit unitAnswer;
        private MainUnit unitDeath;
        private Long personId;
        private LocalDateTime timeActive;
        private LocalDateTime timeDeath;
        private Integer periodSec;
        private CheckData checkLoop;

        private Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder unitAnswer(MainUnit val) {
            unitAnswer = val;
            return this;
        }

        public Builder unitDeath(MainUnit val) {
            unitDeath = val;
            return this;
        }

        public Builder personId(Long val) {
            personId = val;
            return this;
        }

        public Builder timeActive(LocalDateTime val) {
            timeActive = val;
            return this;
        }

        public Builder timeDeath(LocalDateTime val) {
            timeDeath = val;
            return this;
        }

        public Builder periodSec(Integer val) {
            periodSec = val;
            return this;
        }

        public Builder checkLoop(CheckData val) {
            checkLoop = val;
            return this;
        }

        public Timer build() {
            return new Timer(this);
        }
    }
}
