package dev.struchkov.godfather.main.core.utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static dev.struchkov.haiti.utils.Exceptions.utilityClass;

/**
 * Класс для вставки слов в текстовую строку вместо подстрок - шаблонов маркеров.
 *
 * @author upagge [08/07/2019]
 */
public class InsertWords {

    private static final Pattern pattern = Pattern.compile("\\{(\\d+)}");

    private InsertWords() {
        utilityClass();
    }

    /**
     * Заменяет шаблон {n} в строке на слово из списка, где n - это порядковое число.
     *
     * @param text  Текстовая строка
     * @param words Список слов, которые необходимо поместить вместо шаблона
     * @return Модифицированная строка
     */
    public static String insert(@NotNull String text, List<String> words) {
        final Matcher m = pattern.matcher(text);
        final StringBuilder result = new StringBuilder();
        while (m.find()) {
            if (Integer.parseInt(m.group(1)) < words.size()) {
                m.appendReplacement(result, words.get(Integer.parseInt(m.group(1))));
            } else {
                m.appendReplacement(result, m.group(0));
            }
        }
        m.appendTail(result);
        return result.toString();
    }

}


