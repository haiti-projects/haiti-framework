package dev.struchkov.haiti.utils;

/**
 * Адаптированная реализация Pair из пакета javafx.util. Реализация необходима, так как в некоторых сборках JDK этот
 * пакет может отсутствовать.
 *
 * @author mstruchkov 21.06.2019
 */
public class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
