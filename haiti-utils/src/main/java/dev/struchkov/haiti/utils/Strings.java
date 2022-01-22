package dev.struchkov.haiti.utils;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Утилитарный класс для работы со строками.
 *
 * @author upagge 07.03.2021
 */
public final class Strings {

    private Strings() {
        utilityClass();
    }

    public static final String EMPTY = "";
    public static final String ERR_UTILITY_CLASS = "Нельзя создать объект утилитарного класса";
    public static final String ERR_OPERATION_NOT_SUPPORTED = "Операция не поддерживается";

}
