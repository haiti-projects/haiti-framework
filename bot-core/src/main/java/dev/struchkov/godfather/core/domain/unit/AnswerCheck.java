package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.utils.Description;
import dev.struchkov.godfather.core.service.usercode.CheckData;
import dev.struchkov.godfather.core.utils.TypeUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик запроса, который реализует конструкцию IF в сценарии.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerCheck extends MainUnit {

    @Description("Unit для true")
    private final MainUnit unitTrue;

    @Description("Unit для false")
    private final MainUnit unitFalse;

    @Description("Условие проверки")
    private final CheckData<Message> check;

    @Builder
    protected AnswerCheck(
            @Singular Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            @Singular Set<MainUnit> nextUnits,
            UnitActiveType activeType,
            MainUnit unitTrue,
            MainUnit unitFalse,
            CheckData<Message> check
    ) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.CHECK);
        this.unitTrue = unitTrue;
        this.unitFalse = unitFalse;
        this.check = check;
    }

}
