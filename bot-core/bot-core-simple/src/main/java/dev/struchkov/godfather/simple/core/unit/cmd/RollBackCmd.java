package dev.struchkov.godfather.simple.core.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.exception.UnitConfigException;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.unit.MainUnit;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Checker.checkNull;

/**
 * Юнит, который позволяет откатить пользователя на предыдущие юниты в сценарии с сохранением ранее введенной информации в сообщениях.
 */
public class RollBackCmd<M extends Message> extends MainUnit<M> {

    /**
     * Количество юнитов, на которые можно откатиться назад.
     */
    private final int countBack;

    /**
     * Имя юнита, на который нужно вернуть пользователя.
     */
    private final String rollbackUnitName;

    private RollBackCmd(Builder<M> builder) {
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
                true,
                null,
                TypeUnit.BACK_CMD
        );
        this.countBack = builder.countBack;
        this.rollbackUnitName = builder.rollbackUnitName;
    }

    public static <M extends Message> RollBackCmd.Builder<M> builder() {
        return new RollBackCmd.Builder<>();
    }

    public static <M extends Message> RollBackCmd<M> rollBack(int countToBack) {
        return RollBackCmd.<M>builder().countBack(countToBack).build();
    }

    public static <M extends Message> RollBackCmd<M> singleRollBack() {
        return RollBackCmd.<M>builder().countBack(1).build();
    }

    public static <M extends Message> RollBackCmd<M> doubleRollBack() {
        return RollBackCmd.<M>builder().countBack(2).build();
    }

    public static <M extends Message> RollBackCmd<M> rollBack(String unitName) {
        return RollBackCmd.<M>builder().rollbackUnitName(unitName).build();
    }

    public static <M extends Message> RollBackCmd<M> rollBack(String phrase, String unitName) {
        return RollBackCmd.<M>builder().triggerPhrase(phrase).rollbackUnitName(unitName).build();
    }

    public int getCountBack() {
        return countBack;
    }

    public String getRollbackUnitName() {
        return rollbackUnitName;
    }

    public static final class Builder<M extends Message> {
        private String name = UUID.randomUUID().toString();
        private String description;

        private Set<String> triggerPhrases;
        private Predicate<M> triggerCheck;
        private Set<Pattern> triggerPatterns;
        private Set<KeyWord> triggerWords;
        private Integer matchThreshold;

        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.DEFAULT;

        private int countBack;
        private String rollbackUnitName;

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
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val);
            return this;
        }

        public Builder<M> triggerWord(KeyWord val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(val);
            return this;
        }

        public Builder<M> triggerStringWords(Set<String> val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder<M> triggerWord(String val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(KeyWord.of(val));
            return this;
        }

        public Builder<M> triggerPhrase(String... val) {
            if (checkNull(triggerPhrases)) {
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

        public Builder<M> triggerCheck(Predicate<M> trigger) {
            triggerCheck = trigger;
            return this;
        }

        public Builder<M> triggerPattern(Pattern... val) {
            if (checkNull(triggerPatterns)) {
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

        public Builder<M> matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder<M> priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder<M> activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public Builder<M> countBack(int val) {
            countBack = val + 1;
            return this;
        }

        public Builder<M> rollbackUnitName(String val) {
            rollbackUnitName = val;
            return this;
        }

        public RollBackCmd<M> build() {
            if (rollbackUnitName == null && countBack < 2) {
                throw new UnitConfigException("Ошибка конфигурирования юнита {0}: Количество юнитов для отката не должно быть меньше 1.", name);
            }
            return new RollBackCmd<>(this);
        }

    }

}
