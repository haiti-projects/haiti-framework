package dev.struchkov.haiti.context.domain;

/**
 * Базовая класс для сущностей.
 *
 * @author upagge 14.01.2021
 */
public interface BasicEntity<Key> {

    Key getId();

    void setId(Key id);

}
