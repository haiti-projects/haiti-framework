package dev.struchkov.godfather.simple.core.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.main.core.unit.TypeUnit;
import dev.struchkov.godfather.main.core.unit.UnitActiveType;
import dev.struchkov.godfather.main.domain.content.Message;
import dev.struchkov.godfather.simple.core.unit.MainUnit;
import dev.struchkov.haiti.utils.Checker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReplaceCmd<M extends Message> extends MainUnit<M> {

    private final MainUnit thisUnit;

    private ReplaceCmd(Builder<M> builder) {
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
                TypeUnit.REPLACE_CMD
        );
        this.thisUnit = builder.thisUnit;
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public MainUnit getThisUnit() {
        return thisUnit;
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
        private UnitActiveType activeType = UnitActiveType.AFTER;

        private MainUnit thisUnit;

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
            if (Checker.checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val);
            return this;
        }

        public Builder<M> triggerWord(KeyWord val) {
            if (Checker.checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(val);
            return this;
        }

        public Builder<M> triggerStringWords(Set<String> val) {
            if (Checker.checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder<M> triggerWord(String val) {
            if (Checker.checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(KeyWord.of(val));
            return this;
        }

        public Builder<M> triggerPhrase(String... val) {
            if (Checker.checkNull(triggerPhrases)) {
                triggerPhrases = new HashSet<>();
            }
            if (val.length == 1) {
                triggerPhrases.add(val[0]);
            } else {
                triggerPhrases.addAll(Set.of(val));
            }
            return this;
        }

        public Builder<M> triggerPattern(Pattern... val) {
            if (Checker.checkNull(triggerPatterns)) {
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

        public Builder<M> activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public Builder<M> thisUnit(MainUnit val) {
            thisUnit = val;
            return this;
        }

        public ReplaceCmd<M> build() {
            return new ReplaceCmd<>(this);
        }

    }

}
