package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.core.service.ClarificationQuestion;
import dev.struchkov.godfather.core.service.save.LocalPreservable;
import dev.struchkov.godfather.core.service.save.Preservable;
import dev.struchkov.godfather.core.utils.TypeUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработка данных со страницы пользователя.
 *
 * @author upagge [11/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class AnswerValidity extends MainUnit {

    /**
     * Unit обрабатывается, если пользователь подтверждает данные.
     */
    private final MainUnit unitYes;

    /**
     * Unit обрабатывается, если пользователь отклоняет данные.
     */
    private final MainUnit unitNo;

    /**
     * Unit обрабатывается, если данные не найдены.
     */
    private final MainUnit unitNull;

    private final Preservable<String> tempSave = new LocalPreservable<>();

    private final ClarificationQuestion clarificationQuestion;

    @Builder(toBuilder = true)
    private AnswerValidity(
            @Singular Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            @Singular Set<MainUnit> nextUnits,
            MainUnit unitYes,
            MainUnit unitNo,
            MainUnit unitNull,
            ClarificationQuestion clarificationQuestion
    ) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, UnitActiveType.DEFAULT, TypeUnit.VALIDITY);
        this.unitYes = unitYes;
        this.unitNo = unitNo;
        this.unitNull = unitNull;
        this.clarificationQuestion = clarificationQuestion;
    }

}
