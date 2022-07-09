package dev.struchkov.godfather.context.domain.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.UnitActiveType;
import dev.struchkov.godfather.context.exception.UnitConfigException;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Юнит, который позволяет откатить пользователя на предыдущие юниты в сценарии с сохранением ранее введенной информации в сообщениях.
 */
public class RollBackCmd extends MainUnit {

    /**
     * Количество юнитов, на которые можно откатиться назад.
     */
    private final int countBack;

    private RollBackCmd(Builder builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrase,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                new HashSet<>(),
                builder.activeType,
                null,
                TypeUnit.BACK_CMD
        );
        this.countBack = builder.countBack;
    }

    public int getCountBack() {
        return countBack;
    }

    public static RollBackCmd.Builder builder() {
        return new RollBackCmd.Builder();
    }

    public static RollBackCmd rollBack(int countToBack) {
        return RollBackCmd.builder().countBack(countToBack).build();
    }

    public static RollBackCmd singleRollBack() {
        return RollBackCmd.builder().countBack(1).build();
    }

    public static final class Builder {
        private String name;
        private Set<KeyWord> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.DEFAULT;
        private int countBack;

        private Builder() {
        }

        public RollBackCmd.Builder name(String name) {
            this.name = name;
            return this;
        }

        public RollBackCmd.Builder keyWords(Set<KeyWord> val) {
            keyWords.addAll(val);
            return this;
        }

        public RollBackCmd.Builder keyWord(KeyWord val) {
            keyWords.add(val);
            return this;
        }

        public RollBackCmd.Builder stringKeyWords(Set<String> val) {
            keyWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public RollBackCmd.Builder keyWord(String val) {
            keyWords.add(KeyWord.of(val));
            return this;
        }

        public RollBackCmd.Builder phrase(String val) {
            phrase = val;
            return this;
        }

        public RollBackCmd.Builder pattern(Pattern val) {
            pattern = val;
            return this;
        }

        public RollBackCmd.Builder matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public RollBackCmd.Builder priority(Integer val) {
            priority = val;
            return this;
        }

        public RollBackCmd.Builder activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public RollBackCmd.Builder countBack(int val) {
            countBack = val + 1;
            return this;
        }

        public RollBackCmd build() {
            if (countBack < 2) {
                throw new UnitConfigException("Ошибка конфигурирования юнита {0}: Количество юнитов для отката не должно быть меньше 1.", name);
            }
            return new RollBackCmd(this);
        }

    }

}
