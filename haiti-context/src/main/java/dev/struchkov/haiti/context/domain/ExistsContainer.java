package dev.struchkov.haiti.context.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/**
 * Контейнер для возврата результата сервисных методов exists.
 *
 * @author upagge 11.01.2021
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ExistsContainer<T, K> implements Serializable {

    protected final Collection<T> container;
    protected final boolean allFound;
    protected final Collection<K> idNoFound;

    public static <T, K> ExistsContainer<T, K> allFind(@NonNull Collection<T> container) {
        return new ExistsContainer<>(container, true, Collections.emptyList());
    }

    public static <T, K> ExistsContainer<T, K> notAllFind(@NonNull Collection<T> container, @NonNull Collection<K> idNoFound) {
        return new ExistsContainer<>(container, false, idNoFound);
    }

}
