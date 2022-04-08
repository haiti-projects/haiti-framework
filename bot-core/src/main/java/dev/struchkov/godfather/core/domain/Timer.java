package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.service.usercode.CheckData;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Используется для отложенной активации обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@Getter
@Setter
@Builder
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

}
