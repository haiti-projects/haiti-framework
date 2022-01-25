package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.utils.Description;
import dev.struchkov.godfather.context.service.usercode.CheckData;
import dev.struchkov.godfather.core.utils.TypeUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик таймер, позволяющий отложить обработку других Unit-ов.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AnswerTimer extends MainUnit {

    @Description("Unit обработку которого необходимо отложить")
    private final MainUnit unitAnswer;

    @Description("Задержка обработки в секундах")
    private final Integer timeDelaySec;

    @Description("Время, через которое таймер будет удален в секундах")
    private final Integer timeDeathSec;

    @Description("Условие срабатывания отложенного Unit")
    private final CheckData checkLoop;

    @Builder
    private AnswerTimer(@Singular Set<String> keyWords,
                        String phrase,
                        Pattern pattern,
                        Integer matchThreshold,
                        Integer priority,
                        @Singular Set<MainUnit> nextUnits,
                        UnitActiveType activeType,
                        MainUnit unitAnswer,
                        Integer timeDelaySec,
                        Integer timeDeathSec,
                        CheckData checkLoop) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, (activeType == null) ? UnitActiveType.AFTER : activeType, TypeUnit.TIMER);
        this.unitAnswer = unitAnswer;
        this.timeDelaySec = timeDelaySec;
        this.timeDeathSec = timeDeathSec;
        this.checkLoop = checkLoop;
    }

}
