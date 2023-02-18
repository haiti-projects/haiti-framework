package dev.struchkov.haiti.utils.container;

import java.util.Objects;

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

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                ContextKey<?> contextKey = (ContextKey<?>) o;
                return Objects.equals(value, contextKey.getValue());
            }

            @Override
            public int hashCode() {
                return Objects.hash(value);
            }
        };
    }

}
