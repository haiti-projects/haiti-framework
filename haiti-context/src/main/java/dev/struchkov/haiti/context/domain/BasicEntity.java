package dev.struchkov.haiti.context.domain;

import lombok.NonNull;

/**
 * // TODO: 14.01.2021 Добавить описание.
 *
 * @author upagge 14.01.2021
 */
public interface BasicEntity<K> {

    K getId();

    void setId(@NonNull K id);

}
