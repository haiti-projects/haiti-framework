package dev.struchkov.godfather.core.domain.unit;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.core.service.save.CheckSave;
import dev.struchkov.godfather.core.service.save.Preservable;
import dev.struchkov.godfather.core.service.save.data.PreservableData;
import dev.struchkov.godfather.core.service.save.push.Pusher;
import dev.struchkov.godfather.core.utils.TypeUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Обработчик для сохранения ответов пользователя. Так же допускается скрытое сохранение.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
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

    @Builder
    private AnswerSave(
            @Singular Set<String> keyWords,
            String phrase,
            Pattern pattern,
            Integer matchThreshold,
            Integer priority,
            @Singular Set<MainUnit> nextUnits,
            Preservable<D> preservable,
            String key,
            Pusher<D> pusher,
            PreservableData<D, ? super Message> preservableData,
            CheckSave<? super Message> checkSave,
            boolean hidden
    ) {
        super(keyWords, phrase, pattern, matchThreshold, priority, nextUnits, (hidden) ? UnitActiveType.AFTER : UnitActiveType.DEFAULT, TypeUnit.SAVE);
        this.key = key;
        this.pusher = pusher;
        maintenanceNextUnit(nextUnits);
        this.preservable = preservable;
        this.preservableData = preservableData;
        this.hidden = Optional.of(hidden).orElse(false);
        this.checkSave = checkSave;
    }

    private void maintenanceNextUnit(Collection<MainUnit> units) {
        if (units != null) {
            units.forEach(mainUnit -> mainUnit.setActiveType(UnitActiveType.AFTER));
        }
    }

}
