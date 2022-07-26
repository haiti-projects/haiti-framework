package dev.struchkov.haiti.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public final class CollectionUtils {

    private CollectionUtils() {
        utilityClass();
    }

    public static <T> List<T> toList(T... t) {
        return Arrays.stream(t).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollection(T... t) {
        return Arrays.stream(t).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollection(Collection<T> collection, T... t) {
        return Stream.concat(
                collection.stream(), Arrays.stream(t)
        ).collect(Collectors.toList());
    }

    public static <T> Collection<T> toCollection(Collection<T> collectionOne, Collection<T> collectionTwo) {
        return Stream.concat(
                collectionOne.stream(), collectionTwo.stream()
        ).collect(Collectors.toList());
    }

}
