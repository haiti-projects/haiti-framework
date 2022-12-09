package dev.struchkov.haiti.utils;

import java.util.Collection;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;
import static dev.struchkov.haiti.utils.Strings.EMPTY;

public final class Checker {

    private Checker() {
        utilityClass();
    }

    public static boolean checkNull(Object o) {
        return o == null;
    }

    public static boolean checkAllNull(Object... objects) {
        if (objects.length == 0) return false;

        for (Object object : objects)
            if (checkNotNull(object))
                return false;

        return true;
    }

    public static boolean checkNotNull(Object o) {
        return o != null;
    }

    public static boolean checkAllNotNull(Object... objects) {
        if (objects.length == 0) return false;

        for (Object object : objects)
            if (checkNull(object))
                return false;

        return true;
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

    public static boolean checkNotEmpty(String s) {
        return !EMPTY.equals(s);
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

    public static boolean checkEmpty(String s) {
        return EMPTY.equals(s);
    }

    public static boolean checkNotFalse(Boolean b) {
        return b != null && b;
    }

    public static boolean checkNotTrue(Boolean b) {
        return b != null && !b;
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
