package dev.struchkov.godfather.context.domain.unit.cmd;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.unit.MainUnit;
import dev.struchkov.godfather.context.domain.unit.UnitActiveType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Checker.checkNull;

/**
 * Позволяет перенести пользователя в произвольное место в сценарии.
 */
public class TeleportCmd<M extends Message> extends MainUnit<M> {

    /**
     * Название юнита, в которое необходимо осуществить перенос.
     */
    private final String unitNameToTeleport;

    private TeleportCmd(Builder<M> builder) {
        super(
                builder.name,
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
                TypeUnit.TELEPORT_CMD
        );
        this.unitNameToTeleport = builder.unitNameToTeleport;
    }

    public static <M extends Message> Builder<M> builder() {
        return new Builder<>();
    }

    public String getUnitNameToTeleport() {
        return unitNameToTeleport;
    }

    public static final class Builder<M extends Message> {
        private String name;

        private Set<KeyWord> triggerWords;
        private Set<String> triggerPhrases;
        private Set<Pattern> triggerPatterns;
        private Predicate<M> triggerCheck;
        private Integer matchThreshold;

        private Integer priority;
        private UnitActiveType activeType = UnitActiveType.DEFAULT;
        private String unitNameToTeleport;

        private Builder() {
        }

        public Builder<M> name(String name) {
            this.name = name;
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

        public Builder<M> unitNameToTeleport(String val) {
            unitNameToTeleport = val;
            return this;
        }

        public TeleportCmd<M> build() {
            return new TeleportCmd<>(this);
        }

    }

}
