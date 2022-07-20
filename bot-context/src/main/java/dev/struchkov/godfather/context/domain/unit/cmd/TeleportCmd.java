package dev.struchkov.godfather.context.domain.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.UnitActiveType;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Позволяет перенести пользователя в произвольное место в сценарии.
 */
public class TeleportCmd extends MainUnit {

    /**
     * Название юнита, в которое необходимо осуществить перенос.
     */
    private final String unitNameToTeleport;

    private TeleportCmd(Builder builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrases,
                builder.triggerCheck,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                new HashSet<>(),
                builder.activeType,
                true,
                null,
                TypeUnit.TELEPORT_CMD
        );
        this.unitNameToTeleport = builder.unitNameToTeleport;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getUnitNameToTeleport() {
        return unitNameToTeleport;
    }

    public static final class Builder {
        private final Set<KeyWord> keyWords = new HashSet<>();
        private final Set<String> phrases = new HashSet<>();
        private Predicate<String> triggerCheck;
        private String name;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.DEFAULT;
        private String unitNameToTeleport;

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

        public Builder triggerCheck(Predicate<String> trigger) {
            triggerCheck = trigger;
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

        public Builder unitNameToTeleport(String val) {
            unitNameToTeleport = val;
            return this;
        }

        public TeleportCmd build() {
            return new TeleportCmd(this);
        }

    }

}
