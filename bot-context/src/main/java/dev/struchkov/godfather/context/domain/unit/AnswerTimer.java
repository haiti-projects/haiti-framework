package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.usercode.CheckData;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.struchkov.godfather.context.exception.UnitConfigException.unitConfigException;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Обработчик таймер, позволяющий отложить обработку других Unit-ов.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerTimer<M extends Message> extends MainUnit<M> {

    /**
     * Unit обработку которого необходимо отложить.
     */
    private final MainUnit<M> unitAnswer;

    /**
     * Задержка обработки в секундах.
     */
    private final Integer timeDelaySec;

    /**
     * Время, через которое таймер будет удален в секундах.
     */
    private final Integer timeDeathSec;

    /**
     * Условие срабатывания отложенного Unit.
     */
    private final CheckData<M> checkLoop;

    private AnswerTimer(Builder<M> builder) {
        super(
                builder.name,
                builder.triggerWords,
                builder.triggerPhrases,
                builder.triggerCheck,
                builder.triggerPatterns,
                builder.matchThreshold,
                builder.priority,
                null,
                builder.activeType,
                builder.notSaveHistory,
                builder.accessibility,
                TypeUnit.TIMER
        );
        unitAnswer = builder.unitAnswer;
        timeDelaySec = builder.timeDelaySec;
        timeDeathSec = builder.timeDeathSec;
        checkLoop = builder.checkLoop;
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public MainUnit<M> getUnitAnswer() {
        return unitAnswer;
    }

    public Integer getTimeDelaySec() {
        return timeDelaySec;
    }

    public Integer getTimeDeathSec() {
        return timeDeathSec;
    }

    public CheckData<M> getCheckLoop() {
        return checkLoop;
    }

    public static final class Builder<M extends Message> {
        private String name;

        private Set<KeyWord> triggerWords;
        private Set<String> triggerPhrases;
        private Predicate<M> triggerCheck;
        private Set<Pattern> triggerPatterns;
        private Integer matchThreshold;

        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.AFTER;
        private Accessibility accessibility;
        private boolean notSaveHistory;

        private MainUnit<M> unitAnswer;
        private Integer timeDelaySec;
        private Integer timeDeathSec;
        private CheckData<M> checkLoop;

        private Builder() {
        }

        public Builder<M> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<M> unitAnswer(MainUnit<M> val) {
            unitAnswer = val;
            return this;
        }

        public Builder<M> timeDelaySec(Integer val) {
            timeDelaySec = val;
            return this;
        }

        public Builder<M> timeDeathSec(Integer val) {
            timeDeathSec = val;
            return this;
        }

        public Builder<M> checkLoop(CheckData<M> val) {
            checkLoop = val;
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

        public AnswerTimer<M> build() {
            isNotNull(unitAnswer, unitConfigException("Необходимо указать юнит, обработка которого будет отложена."));
            return new AnswerTimer<>(this);
        }

    }

}
