package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.UnitConfigException;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.service.usercode.Insert;
import dev.struchkov.godfather.context.service.usercode.MessageFunction;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;
import dev.struchkov.godfather.core.utils.TypeUnit;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;

import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Используется для отправки ответа пользователю.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerText<M extends Message> extends MainUnit {

    /**
     * Объект, который необходимо отправить пользователю.
     */
    private final ProcessingData<M> boxAnswer;

    /**
     * Информация, которую необходимо вставить вместо маркеров в строку ответа.
     */
    private final Insert insert;

    /**
     * Объект нестандартной отправки ответа.
     */
    private final Sending sending;

    private AnswerText(Builder<M> builder) {
        super(builder.keyWords, builder.phrase, builder.pattern, builder.matchThreshold, builder.priority, builder.nextUnits, builder.activeType, TypeUnit.TEXT);
        boxAnswer = builder.boxAnswer;
        insert = builder.insert;
        sending = builder.sending;
    }

    public static <M extends Message> AnswerText<M> of(String message) {
        return AnswerText.<M>builder().boxAnswer(BoxAnswer.boxAnswer(message)).build();
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public ProcessingData<M> getBoxAnswer() {
        return boxAnswer;
    }

    public Insert getInsert() {
        return insert;
    }

    public Sending getSending() {
        return sending;
    }

    public static final class Builder<M extends Message> {
        private ProcessingData<M> boxAnswer;
        private Insert insert;
        private Sending sending;
        private Set<String> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private Set<MainUnit> nextUnits = new HashSet<>();
        private UnitActiveType activeType;

        private Builder() {
        }

        public Builder<M> message(ProcessingData<M> message) {
            this.boxAnswer = message;
            return this;
        }

        public Builder<M> message(MessageFunction<M> function) {
            this.boxAnswer = message -> {
                final BoxAnswer.Builder builder = BoxAnswer.builder();
                function.build(message, builder);
                return builder.build();
            };
            return this;
        }

        public Builder<M> boxAnswer(Consumer<BoxAnswer.Builder> boxAnswer) {
            final BoxAnswer.Builder boxAnswerBuilder = BoxAnswer.builder();
            boxAnswer.accept(boxAnswerBuilder);
            this.boxAnswer = message -> boxAnswerBuilder.build();
            return this;
        }

        public Builder<M> boxAnswer(BoxAnswer boxAnswer) {
            this.boxAnswer = message -> boxAnswer;
            return this;
        }

        public Builder<M> insert(Insert insert) {
            this.insert = insert;
            return this;
        }

        public Builder<M> sending(Sending sending) {
            this.sending = sending;
            return this;
        }

        public Builder<M> keyWords(Set<String> val) {
            keyWords = val;
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

        public Builder<M> activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public AnswerText<M> build() {
            isNotNull(boxAnswer, UnitConfigException.unitConfigException("BoxAnswer обязательный параметр юнита"));
            return new AnswerText<>(this);
        }

    }
}
