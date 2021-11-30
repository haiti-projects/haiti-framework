package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.utils.Description;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import dev.struchkov.godfather.core.service.usercode.CheckData;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Используется для отложенной активации обработки Unit-ов.
 *
 * @author upagge [11/07/2019]
 */
@Builder
@Data
public class Timer {

    @Description("Идентификатор таймера")
    private Integer id;

    @Description("Unit, обработка которого откладывается")
    private MainUnit unitAnswer;

    @Description("Unit, который будет обработан после удаления таймера")
    private MainUnit unitDeath;

    @Description("Идентификатор пользователя")
    private Long personId;

    @Description("Время активации таймера")
    private LocalDateTime timeActive;

    @Description("Время смерти таймера")
    private LocalDateTime timeDeath;

    @Description("Интервал срабатывания таймера")
    private Integer periodSec;

    @Description("Условие срабатывания таймера")
    private CheckData checkLoop;

}
