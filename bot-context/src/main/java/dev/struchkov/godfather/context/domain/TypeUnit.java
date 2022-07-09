package dev.struchkov.godfather.context.domain;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Тип Unit-а. Обределяет способ обработки.
 *
 * @author upagge [11/07/2019]
 */
public class TypeUnit {

    public static final String TEXT = "TEXT";
    public static final String SAVE = "SAVE";
    public static final String TIMER = "TIMER";
    public static final String CHECK = "CHECK";
    public static final String VALIDITY = "VALIDITY";
    public static final String BACK_CMD = "BACK_CMD";

    public static final String TELEPORT_CMD = "TELEPORT_CMD";

    private TypeUnit() {
        utilityClass();
    }


}
