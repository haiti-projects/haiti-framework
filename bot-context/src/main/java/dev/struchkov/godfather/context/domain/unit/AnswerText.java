package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.exception.UnitConfigException;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.sender.Sending;
import dev.struchkov.godfather.context.service.usercode.Insert;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        super(
                builder.name,
                builder.keyWords,
                builder.phrases,
                builder.triggerCheck,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                builder.nextUnits,
                builder.activeType,
                builder.notSaveHistory,
                builder.accessibility,
                TypeUnit.TEXT
        );
        boxAnswer = builder.boxAnswer;
        insert = builder.insert;
        sending = builder.sending;
    }

    public static <M extends Message> AnswerText<M> of(String message) {
        return AnswerText.<M>builder().boxAnswer(BoxAnswer.boxAnswer(message)).build();
    }

    public static <M extends Message> AnswerText<M> of(BoxAnswer boxAnswer) {
        return AnswerText.<M>builder().boxAnswer(boxAnswer).build();
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
        private final Set<KeyWord> keyWords = new HashSet<>();
        private final Set<String> phrases = new HashSet<>();
        private Predicate<String> triggerCheck;
        private String name;
        private ProcessingData<M> boxAnswer;
        private Insert insert;
        private Sending sending;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private Set<MainUnit> nextUnits = new HashSet<>();
        private UnitActiveType activeType;
        private Accessibility accessibility;
        private boolean notSaveHistory;

        private Builder() {
        }

        public Builder<M> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<M> message(ProcessingData<M> message) {
            this.boxAnswer = message;
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

        public Builder<M> keyWords(Set<KeyWord> val) {
            keyWords.addAll(val);
            return this;
        }

        public Builder<M> keyWord(KeyWord val) {
            keyWords.add(val);
            return this;
        }

        public Builder<M> stringKeyWords(Set<String> val) {
            keyWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder<M> keyWord(String val) {
            keyWords.add(KeyWord.of(val));
            return this;
        }

        public Builder<M> phrase(String val) {
            phrases.add(val);
            return this;
        }

        public Builder<M> phrases(String... val) {
            phrases.addAll(Arrays.asList(val));
            return this;
        }

        public Builder<M> triggerCheck(Predicate<String> trigger) {
            triggerCheck = trigger;
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

        public Builder<M> accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public Builder<M> notSaveHistory() {
            notSaveHistory = true;
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
