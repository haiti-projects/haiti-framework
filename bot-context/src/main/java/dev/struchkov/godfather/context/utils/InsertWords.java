package dev.struchkov.godfather.context.utils;

import lombok.NonNull;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для вставки слов в текстовую строку вместо подстрок - шаблонов маркеров.
 *
 * @author upagge [08/07/2019]
 */
public class InsertWords {

    private static final Pattern pattern = Pattern.compile("\\{(\\d+)}");

    private InsertWords() {
        throw new IllegalStateException(Messages.UTILITY_CLASS);
    }

    /**
     * Заменяет шаблон {n} в строке на слово из списка, где n - это порядковое число.
     *
     * @param text  Текстовая строка
     * @param words Список слов, которые необходимо поместить вместо шаблона
     * @return Модифицированная строка
     */
    public static String insert(@NonNull String text, List<String> words) {
        Matcher m = pattern.matcher(text);
        StringBuffer result = new StringBuffer();
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


