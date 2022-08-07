package dev.struchkov.godfather.main.domain;

public interface ContextKey<T> {

    String getValue();

    Class<T> getType();

    static <T> ContextKey<T> of(String value, Class<T> type) {
        return new ContextKey<>() {
            @Override
            public String getValue() {
                return value;
            }

            @Override
            public Class<T> getType() {
                return type;
            }
        };
    }

}
