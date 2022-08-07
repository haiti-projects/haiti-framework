package dev.struchkov.godfather.quarkus.core.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Accessibility;
import dev.struchkov.godfather.quarkus.context.service.Sending;
import dev.struchkov.godfather.quarkus.core.unit.func.Insert;
import dev.struchkov.godfather.quarkus.core.unit.func.ProcessingData;
import io.smallrye.mutiny.Uni;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Используется для отправки ответа пользователю.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerText<M extends Message> extends MainUnit<M> {

    /**
     * Объект, который необходимо отправить пользователю.
     */
    private final ProcessingData<M> answer;

    /**
     * Информация, которую необходимо вставить вместо маркеров в строку ответа.
     */
    private final Insert insert;

    /**
     * Объект нестандартной отправки ответа.
     */
    private final Sending sender;

    private AnswerText(Builder<M> builder) {
        super(
                builder.name,
                builder.description,
                builder.triggerWords,
                builder.triggerPhrases,
                builder.triggerCheck,
                builder.triggerPatterns,
                builder.matchThreshold,
                builder.priority,
                builder.nextUnits,
                builder.activeType,
                builder.notSaveHistory,
                builder.accessibility,
                TypeUnit.TEXT
        );
        answer = builder.boxAnswer;
        insert = builder.insert;
        sender = builder.sending;
    }

    public static <M extends Message> AnswerText<M> of(String message) {
        return AnswerText.<M>builder().answer(BoxAnswer.boxAnswer(message)).build();
    }

    public static <M extends Message> AnswerText<M> of(BoxAnswer boxAnswer) {
        return AnswerText.<M>builder().answer(boxAnswer).build();
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public ProcessingData<M> getAnswer() {
        return answer;
    }

    public Insert getInsert() {
        return insert;
    }

    public Sending getSending() {
        return sender;
    }

    public static final class Builder<M extends Message> {
        private String name = UUID.randomUUID().toString();
        private String description;
        private Set<MainUnit<M>> nextUnits;

        private Set<KeyWord> triggerWords;
        private Set<String> triggerPhrases;
        private Predicate<M> triggerCheck;
        private Set<Pattern> triggerPatterns;

        private Integer matchThreshold;
        private Integer priority;

        private UnitActiveType activeType;
        private Accessibility accessibility;
        private boolean notSaveHistory;

        private ProcessingData<M> boxAnswer;
        private Insert insert;
        private Sending sending;

        private Builder() {
        }

        public Builder<M> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<M> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<M> processing(Consumer<M> answer) {
            this.boxAnswer = message -> {
                answer.accept(message);
                return null;
            };
            return this;
        }

        public Builder<M> answer(Function<M, Uni<BoxAnswer>> answer) {
            this.boxAnswer = answer::apply;
            return this;
        }

        public Builder<M> answer(BoxAnswer answer) {
            this.boxAnswer = message -> Uni.createFrom().item(answer);
            return this;
        }

        public Builder<M> answer(Supplier<BoxAnswer> answer) {
            this.boxAnswer = message -> Uni.createFrom().item(answer.get());
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

        public Builder<M> triggerWords(Set<KeyWord> val) {
            if (triggerWords == null) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val);
            return this;
        }

        public Builder<M> triggerWord(KeyWord val) {
            if (triggerWords == null) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(val);
            return this;
        }

        public Builder<M> triggerStringWords(Set<String> val) {
            if (triggerWords == null) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder<M> triggerWord(String val) {
            if (triggerWords == null) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(KeyWord.of(val));
            return this;
        }

        public Builder<M> triggerPhrase(String... val) {
            if (triggerPhrases == null) {
                triggerPhrases = new HashSet<>();
            }
            if (val.length == 1) {
                triggerPhrases.add(val[0]);
            } else {
                triggerPhrases.addAll(Set.of(val));
            }
            triggerPhrases.addAll(List.of(val));
            return this;
        }

        public Builder<M> triggerPattern(Pattern... val) {
            if (triggerPatterns == null) {
                triggerPatterns = new HashSet<>();
            }
            if (val.length == 1) {
                triggerPatterns.add(val[0]);
            } else {
                triggerPatterns.addAll(Set.of(val));
            }
            triggerPatterns.addAll(Set.of(val));
            return this;
        }

        public Builder<M> triggerCheck(Predicate<M> trigger) {
            triggerCheck = trigger;
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

        public Builder<M> next(MainUnit<M> val) {
            if (nextUnits == null) {
                nextUnits = new HashSet<>();
            }
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
//            isNotNull(boxAnswer, UnitConfigException.unitConfigException("BoxAnswer обязательный параметр юнита"));
            return new AnswerText<>(this);
        }

    }
}
