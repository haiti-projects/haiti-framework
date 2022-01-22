package dev.struchkov.haiti.context.domain;

import dev.struchkov.haiti.utils.Assert;

import java.util.Collection;
import java.util.Collections;

/**
 * Контейнер для возврата результата сервисных методов exists.
 *
 * @author upagge 11.01.2021
 */
public class ExistsContainer<Entity, Key> {

    protected final Collection<Entity> container;
    protected final boolean allFound;
    protected final Collection<Key> idNoFound;

    protected ExistsContainer(Collection<Entity> container, boolean allFound, Collection<Key> idNoFound) {
        this.container = container;
        this.allFound = allFound;
        this.idNoFound = idNoFound;
    }

    public static <T, K> ExistsContainer<T, K> allFind(Collection<T> container) {
        Assert.isNotNull(container);
        return new ExistsContainer<>(container, true, Collections.emptyList());
    }

    public static <T, K> ExistsContainer<T, K> notAllFind(Collection<T> container, Collection<K> idNoFound) {
        Assert.isNotNull(container, idNoFound);
        return new ExistsContainer<>(container, false, idNoFound);
    }

    public Collection<Entity> getContainer() {
        return container;
    }

    public boolean isAllFound() {
        return allFound;
    }

    public Collection<Key> getIdNoFound() {
        return idNoFound;
    }
}