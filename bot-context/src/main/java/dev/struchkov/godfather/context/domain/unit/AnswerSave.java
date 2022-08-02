package dev.struchkov.godfather.context.domain.unit;

import dev.struchkov.autoresponder.entity.KeyWord;
import dev.struchkov.godfather.context.domain.TypeUnit;
import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.repository.preser.AnswerSavePreservable;
import dev.struchkov.godfather.context.service.Accessibility;
import dev.struchkov.godfather.context.service.save.CheckSave;
import dev.struchkov.godfather.context.service.save.PreservableData;
import dev.struchkov.godfather.context.service.save.Pusher;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.struchkov.haiti.utils.Checker.checkNull;
import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Обработчик для сохранения ответов пользователя. Так же допускается скрытое сохранение.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerSave<M extends Message, D> extends MainUnit<M> {

    /**
     * Объект отвечающий за сохранение - репозиторий.
     */
    private final AnswerSavePreservable<D> preservable;

    /**
     * Ключ для данных.
     */
    private final String key;

    /**
     * Отправка результатов.
     */
    private final Pusher<D> pusher;

    /**
     * Данные для скрытого сохранения.
     */
    private final PreservableData<D, M> preservableData;

    /**
     * Скрытое сохранение.
     */
    private final boolean hidden;

    private final CheckSave<M> checkSave;

    private AnswerSave(Builder<M, D> builder) {
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
                (builder.hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT,
                builder.notSaveHistory,
                builder.accessibility,
                TypeUnit.SAVE
        );
        maintenanceNextUnit(nextUnits);
        preservable = builder.preservable;
        key = builder.key;
        pusher = builder.pusher;
        preservableData = builder.preservableData;
        hidden = builder.hidden;
        checkSave = builder.checkSave;
    }

    public static <M extends Message, D> Builder<M, D> builder() {
        return new Builder<>();
    }

    private void maintenanceNextUnit(Collection<MainUnit<M>> units) {
        if (units != null) {
            units.forEach(mainUnit -> mainUnit.setActiveType(UnitActiveType.AFTER));
        }
    }

    public AnswerSavePreservable<D> getPreservable() {
        return preservable;
    }

    public String getKey() {
        return key;
    }

    public Pusher<D> getPusher() {
        return pusher;
    }

    public PreservableData<D, M> getPreservableData() {
        return preservableData;
    }

    public boolean isHidden() {
        return hidden;
    }

    public CheckSave<M> getCheckSave() {
        return checkSave;
    }

    public static final class Builder<M extends Message, D> {
        private String name = UUID.randomUUID().toString();
        private String description;
        private Set<MainUnit<M>> nextUnits;

        private Set<KeyWord> triggerWords;
        private Set<String> triggerPhrases;
        private Set<Pattern> triggerPatterns;
        private Predicate<M> triggerCheck;

        private Integer matchThreshold;
        private Integer priority;

        private Accessibility accessibility;
        private boolean notSaveHistory;

        private AnswerSavePreservable<D> preservable;
        private String key;
        private Pusher<D> pusher;
        private PreservableData<D, M> preservableData;
        private boolean hidden;
        private CheckSave<M> checkSave;

        private Builder() {
        }

        public Builder<M, D> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<M, D> description(String description) {
            this.description = description;
            return this;
        }

        public Builder<M, D> triggerWords(Set<KeyWord> val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val);
            return this;
        }

        public Builder<M, D> triggerWord(KeyWord val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(val);
            return this;
        }

        public Builder<M, D> triggerStringWords(Set<String> val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.addAll(val.stream().map(KeyWord::of).collect(Collectors.toSet()));
            return this;
        }

        public Builder<M, D> triggerWord(String val) {
            if (checkNull(triggerWords)) {
                triggerWords = new HashSet<>();
            }
            triggerWords.add(KeyWord.of(val));
            return this;
        }

        public Builder<M, D> triggerPhrase(String... val) {
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

        public Builder<M, D> triggerPattern(Pattern... val) {
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

        public Builder<M, D> triggerCheck(Predicate<M> trigger) {
            triggerCheck = trigger;
            return this;
        }

        public Builder<M, D> matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder<M, D> priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder<M, D> next(MainUnit<M> val) {
            if (checkNull(nextUnits)) {
                nextUnits = new HashSet<>();
            }
            nextUnits.add(val);
            return this;
        }

        public Builder<M, D> preservable(AnswerSavePreservable<D> val) {
            this.preservable = val;
            return this;
        }

        public Builder<M, D> key(String val) {
            this.key = val;
            return this;
        }

        public Builder<M, D> pusher(Pusher<D> val) {
            this.pusher = val;
            return this;
        }

        public Builder<M, D> preservableData(PreservableData<D, M> val) {
            this.preservableData = val;
            return this;
        }

        public Builder<M, D> hidden(boolean val) {
            this.hidden = val;
            return this;
        }

        public Builder<M, D> checkSave(CheckSave<M> val) {
            this.checkSave = val;
            return this;
        }

        public Builder<M, D> accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public Builder<M, D> notSaveHistory() {
            notSaveHistory = true;
            return this;
        }

        public AnswerSave<M, D> build() {
            isNotNull(preservable, "Не указан репозиторий для сохранения формы пользователя");
            if (checkNull(pusher)) {
                isNotNull(preservableData, "Не указаны данные для сохранения");
                isNotNull(key, "Не указан ключ для сохранения");
            }
            return new AnswerSave<>(this);
        }

    }

}
