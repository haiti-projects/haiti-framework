package dev.struchkov.godfather.context.domain.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.UnitActiveType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReplaceCmd extends MainUnit {

    private final MainUnit thisUnit;

    private ReplaceCmd(Builder builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrases,
                builder.pattern,
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

    public static Builder builder() {
        return new Builder();
    }

    public MainUnit getThisUnit() {
        return thisUnit;
    }

    public static final class Builder {
        private final Set<KeyWord> keyWords = new HashSet<>();
        private String name;
        private Set<String> phrases = new HashSet<>();
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.AFTER;

        private MainUnit thisUnit;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder keyWords(Set<KeyWord> val) {
            keyWords.addAll(val);
            return this;
        }

        public Builder keyWord(KeyWord val) {
            keyWords.add(val);
            return this;
        }

        public Builder stringKeyWords(Set<String> val) {
            keyWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder keyWord(String val) {
            keyWords.add(KeyWord.of(val));
            return this;
        }

        public Builder phrase(String val) {
            phrases.add(val);
            return this;
        }

        public Builder phrases(Collection<String> val) {
            phrases.addAll(val);
            return this;
        }

        public Builder pattern(Pattern val) {
            pattern = val;
            return this;
        }

        public Builder matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder activeType(UnitActiveType val) {
            activeType = val;
            return this;
        }

        public Builder thisUnit(MainUnit val) {
            thisUnit = val;
            return this;
        }


        public ReplaceCmd build() {
            return new ReplaceCmd(this);
        }

    }

}
