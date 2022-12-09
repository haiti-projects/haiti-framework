package dev.struchkov.godfather.quarkus.core.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.BoxAnswer;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.quarkus.context.service.Accessibility;
import dev.struchkov.godfather.quarkus.core.unit.func.CheckData;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Обработчик запроса, который реализует конструкцию IF в сценарии.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerCheck<M extends Message> extends MainUnit<M> {

    /**
     * Unit для true.
     */
    private final MainUnit unitTrue;

    /**
     * Unit для false.
     */
    private final MainUnit unitFalse;

    /**
     * Временный ответ. Отправляется сразу после проверки условия, если оно true. Предполагается, что после условия следующий Unit может долго обрабатывать какой-то результат. Поэтому можно передать пользователю какое-то сообщение, наподобие: "Подождите идет обработка".
     */
    private final BoxAnswer intermediateAnswerIfTrue;

    /**
     * Промежуточный ответ. Отправляется сразу после проверки условия, если оно false. Предполагается, что после условия следующий Unit может долго обрабатывать какой-то результат. Поэтому можно передать пользователю какое-то сообщение, наподобие: "Подождите идет обработка".
     */
    private final BoxAnswer intermediateAnswerIfFalse;

    /**
     * Условие проверки.
     */
    private final CheckData<M> check;

    private AnswerCheck(Builder<M> builder) {
        super(
                builder.name,
                builder.description,
                builder.triggerWords,
                builder.triggerPhrases,
                builder.triggerCheck,
                builder.triggerPatterns,
                builder.matchThreshold,
                builder.priority,
                new HashSet<>(),
                builder.activeType,
                builder.notSaveHistory,
                builder.accessibility,
                TypeUnit.CHECK
        );
        unitTrue = builder.unitTrue;
        unitFalse = builder.unitFalse;
        check = builder.check;
        intermediateAnswerIfTrue = builder.intermediateAnswerIfTrue;
        intermediateAnswerIfFalse = builder.intermediateAnswerIfFalse;
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public MainUnit getUnitTrue() {
        return unitTrue;
    }

    public MainUnit getUnitFalse() {
        return unitFalse;
    }

    public CheckData<M> getCheck() {
        return check;
    }

    public BoxAnswer getIntermediateAnswerIfTrue() {
        return intermediateAnswerIfTrue;
    }

    public BoxAnswer getIntermediateAnswerIfFalse() {
        return intermediateAnswerIfFalse;
    }

    public static final class Builder<M extends Message> {
        private String name = UUID.randomUUID().toString();
        private String description;

        private Set<KeyWord> triggerWords;
        private Set<String> triggerPhrases;
        private Predicate<M> triggerCheck;
        private Set<Pattern> triggerPatterns;
        private Integer matchThreshold;

        private Integer priority;
        private UnitActiveType activeType;

        private Accessibility accessibility;
        private boolean notSaveHistory;

        private MainUnit unitTrue;
        private MainUnit unitFalse;
        private CheckData<M> check;
        private BoxAnswer intermediateAnswerIfFalse;
        private BoxAnswer intermediateAnswerIfTrue;

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
            triggerPhrases.addAll(Set.of(val));
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

        public Builder<M> unitTrue(MainUnit unitTrue) {
            this.unitTrue = unitTrue;
            return this;
        }

        public Builder<M> unitFalse(MainUnit unitFalse) {
            this.unitFalse = unitFalse;
            return this;
        }

        public Builder<M> check(CheckData<M> check) {
            this.check = check;
            return this;
        }

        public Builder<M> accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public Builder<M> activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public Builder<M> notSaveHistory() {
            notSaveHistory = true;
            return this;
        }

        public Builder<M> intermediateAnswer(BoxAnswer val) {
            intermediateAnswerIfTrue = val;
            intermediateAnswerIfFalse = val;
            return this;
        }

        public Builder<M> intermediateAnswerIfTrue(BoxAnswer val) {
            intermediateAnswerIfTrue = val;
            return this;
        }

        public Builder<M> intermediateAnswerIfFalse(BoxAnswer val) {
            intermediateAnswerIfFalse = val;
            return this;
        }

        public AnswerCheck<M> build() {
//            isNotNull(check, unitConfigException("Необходимо установить параметр проверки."));
//            isAnyNotNull(unitConfigException("Необходимо задать хотя бы один unit результата проверки."));
            return new AnswerCheck<>(this);
        }

    }

}
