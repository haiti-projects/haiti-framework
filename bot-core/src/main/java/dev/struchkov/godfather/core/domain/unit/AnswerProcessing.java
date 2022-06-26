package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;
import dev.struchkov.godfather.core.service.Accessibility;
import dev.struchkov.godfather.core.utils.TypeUnit;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик для кастомных реализаций.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerProcessing<M extends Message> extends MainUnit {

    /**
     * Кастомная обработка.
     */
    private final ProcessingData<M> processingData;

    /**
     * Объект для сквозной отправки ответа.
     */
    private final Sending sending;

    private AnswerProcessing(Builder<M> builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrase,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                builder.nextUnits,
                builder.activeType,
                builder.accessibility,
                TypeUnit.PROCESSING
        );
        processingData = builder.processingData;
        sending = builder.sending;
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public ProcessingData<M> getProcessingData() {
        return processingData;
    }

    public Sending getSending() {
        return sending;
    }

    public static final class Builder<M extends Message> {
        private String name;
        private Set<String> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private Set<MainUnit> nextUnits = new HashSet<>();
        private ProcessingData<M> processingData;
        private Sending sending;
        private UnitActiveType activeType;
        private Accessibility accessibility;

        private Builder() {
        }

        public Builder<M> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<M> processingData(ProcessingData<M> val) {
            processingData = val;
            return this;
        }

        public Builder<M> sending(Sending val) {
            sending = val;
            return this;
        }

        public Builder<M> keyWords(Set<String> val) {
            keyWords = val;
            return this;
        }

        public Builder<M> keyWord(String val) {
            keyWords.add(val);
            return this;
        }

        public Builder<M> phrase(String val) {
            phrase = val;
            return this;
        }

        public Builder<M> pattern(Pattern val) {
            pattern = val;
            return this;
        }

        public Builder<M> matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder<M> priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder<M> nextUnits(Set<MainUnit> val) {
            nextUnits = val;
            return this;
        }

        public Builder<M> nextUnit(MainUnit val) {
            nextUnits.add(val);
            return this;
        }

        public Builder accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public Builder<M> activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public AnswerProcessing<M> build() {
            return new AnswerProcessing<>(this);
        }

    }

}
