package dev.struchkov.haiti.utils;

import java.util.Collection;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

public final class Checker {

    private Checker() {
        utilityClass();
    }

    public static boolean checkNull(Object o) {
        return o == null;
    }

    public static boolean checkNotNull(Object o) {
        return o != null;
    }

    /**
     * Проверка коллекции на пустоту.
     *
     * @return false - если коллекция null или пустая, true в противном случае.
     */
    public static boolean checkNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    /**
     * Проверка массива на пустоту
     *
     * @return false - если массив null или пустой, true в противном случае.
     */
    public static boolean checkNotEmpty(Object... args) {
        if (args == null) {
            return false;
        } else {
            return args.length > 0;
        }
    }

    /**
     * Проверка коллекции на пустоту.
     *
     * @return true - если коллекция null или пустая, false в противном случае
     */
    public static boolean checkEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Проверка массива на пустоту.
     *
     * @return true - если массив null или пустой, false в противном случае
     */
    public static boolean checkEmpty(Object... args) {
        return args == null || args.length == 0;
    }

    public static boolean checkLong(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
