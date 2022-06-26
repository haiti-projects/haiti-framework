package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.service.Accessibility;
import dev.struchkov.godfather.core.service.save.CheckSave;
import dev.struchkov.godfather.core.service.save.Preservable;
import dev.struchkov.godfather.core.service.save.data.PreservableData;
import dev.struchkov.godfather.core.service.save.push.Pusher;
import dev.struchkov.godfather.core.utils.TypeUnit;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static dev.struchkov.haiti.utils.Inspector.isNotNull;

/**
 * Обработчик для сохранения ответов пользователя. Так же допускается скрытое сохранение.
 *
 * @author upagge [08/07/2019]
 */
public class AnswerSave<D> extends MainUnit {

    /**
     * Объект отвечающий за сохранение - репозиторий.
     */
    private final Preservable<D> preservable;

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
    private final PreservableData<D, ? super Message> preservableData;

    /**
     * Скрытое сохранение.
     */
    private final boolean hidden;

    private final CheckSave<? super Message> checkSave;

    private AnswerSave(Builder<D> builder) {
        super(
                builder.name,
                builder.keyWords,
                builder.phrase,
                builder.pattern,
                builder.matchThreshold,
                builder.priority,
                builder.nextUnits,
                (builder.hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT,
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

    public static <D> Builder<D> builder() {
        return new Builder<>();
    }

    private void maintenanceNextUnit(Collection<MainUnit> units) {
        if (units != null) {
            units.forEach(mainUnit -> mainUnit.setActiveType(UnitActiveType.AFTER));
        }
    }

    public Preservable<D> getPreservable() {
        return preservable;
    }

    public String getKey() {
        return key;
    }

    public Pusher<D> getPusher() {
        return pusher;
    }

    public PreservableData<D, ? super Message> getPreservableData() {
        return preservableData;
    }

    public boolean isHidden() {
        return hidden;
    }

    public CheckSave<? super Message> getCheckSave() {
        return checkSave;
    }

    public static final class Builder<D> {
        private String name;
        private Set<String> keyWords = new HashSet<>();
        private String phrase;
        private Pattern pattern;
        private Integer matchThreshold;
        private Integer priority;
        private Set<MainUnit> nextUnits = new HashSet<>();
        private Preservable<D> preservable;
        private String key;
        private Pusher<D> pusher;
        private PreservableData<D, ? super Message> preservableData;
        private boolean hidden;
        private CheckSave<? super Message> checkSave;
        private Accessibility accessibility;

        private Builder() {
        }

        public Builder<D> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<D> keyWords(Set<String> val) {
            keyWords = val;
            return this;
        }

        public Builder<D> keyWord(String val) {
            keyWords.add(val);
            return this;
        }

        public Builder<D> phrase(String val) {
            phrase = val;
            return this;
        }

        public Builder<D> pattern(Pattern val) {
            pattern = val;
            return this;
        }

        public Builder<D> matchThreshold(Integer val) {
            matchThreshold = val;
            return this;
        }

        public Builder<D> priority(Integer val) {
            priority = val;
            return this;
        }

        public Builder<D> nextUnits(Set<MainUnit> val) {
            nextUnits = val;
            return this;
        }

        public Builder<D> nextUnit(MainUnit val) {
            nextUnits.add(val);
            return this;
        }

        public Builder<D> preservable(Preservable<D> val) {
            this.preservable = val;
            return this;
        }

        public Builder<D> key(String val) {
            this.key = val;
            return this;
        }

        public Builder<D> pusher(Pusher<D> val) {
            this.pusher = val;
            return this;
        }

        public Builder<D> preservableData(PreservableData<D, ? super Message> val) {
            this.preservableData = val;
            return this;
        }

        public Builder<D> hidden(boolean val) {
            this.hidden = val;
            return this;
        }

        public Builder<D> checkSave(CheckSave<? super Message> val) {
            this.checkSave = val;
            return this;
        }

        public Builder accessibility(Accessibility val) {
            accessibility = val;
            return this;
        }

        public AnswerSave<D> build() {
            isNotNull(preservable, "Не указан репозиторий для сохранения формы пользователя");
            isNotNull(preservableData, "Не указаны данные для сохранения");
            isNotNull(key, "Не указан ключ для сохранения");
            return new AnswerSave<>(this);
        }

    }

}
