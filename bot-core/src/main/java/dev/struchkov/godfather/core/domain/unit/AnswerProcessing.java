package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.utils.Description;
import dev.struchkov.godfather.core.service.usercode.ProcessingData;
import dev.struchkov.godfather.core.utils.TypeUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик для кастомных реализаций.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class AnswerProcessing<M extends Message> extends MainUnit {

    @Description("Кастомная обработка")
    private final ProcessingData<M> processingData;

    @Description("Объект для сквозной отправки ответа")
    private final Sending sending;

    @Builder
    private AnswerProcessing(@Singular Set<String> keyWords,
                             String phrase,
                             Pattern pattern,
                             Integer matchThreshold,
                             Integer priority,
                             @Singular Set<MainUnit> nextUnits,
                             UnitActiveType activeType,
                             ProcessingData<M> processingData,
                             Sending sending) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, activeType, TypeUnit.PROCESSING);
        this.processingData = processingData;
        this.sending = sending;
    }

}
