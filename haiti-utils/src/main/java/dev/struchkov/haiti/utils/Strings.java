package dev.struchkov.haiti.utils;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Утилитарный класс для работы со строками.
 *
 * @author upagge 07.03.2021
 */
public final class Strings {

    private static final Set<Character> MD_FORBIDDEN_SYMBOLS = Stream.of(
            '\\', '+', '`', '[', ']', '\"', '~', '*', '#', '=', '_', '>', '<'
    ).collect(Collectors.toSet());

    private Strings() {
        utilityClass();
    }

    public static final String EMPTY = "";
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static final String TWO_NEW_LINE = NEW_LINE + NEW_LINE;
    public static final String ERR_UTILITY_CLASS = "Нельзя создать объект утилитарного класса";
    public static final String ERR_OPERATION_NOT_SUPPORTED = "Операция не поддерживается";

    /**
     * <p>Позволяет обрезать строку до нужного количества символов.</p>
     * <p>Если обрезание произошло, то добавляет в конец строки "..."</p>
     * <p>Если вместо строки передали null, то вернет null</p>
     *
     * @param string Строка, которую необходимо обрезать
     * @param length Нужное количество символов
     * @return Обрезанная до length количества символов строка
     */
    public static String cutoff(String string, int length) {
        if (string != null) {
            return string.length() > length ? string.substring(0, length) + "..." : string;
        }
        return null;
    }

    /**
     * Экранирует специальные символы Markdown.
     *
     * @param str Строка, в которой необходимо экранировать символы.
     * @return Строка с экранированными символами
     */
    public static String escapeMarkdown(String str) {
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (MD_FORBIDDEN_SYMBOLS.contains(c)) {
                    sb.append('\\');
                }
                sb.append(c);
            }
            return sb.toString();
        }
        return null;
    }

}
