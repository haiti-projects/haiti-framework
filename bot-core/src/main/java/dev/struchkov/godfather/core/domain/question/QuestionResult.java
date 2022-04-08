package dev.struchkov.godfather.core.domain.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Используется для сохранения результатов ответов на вопросы.
 *
 * @author upagge [14/07/2019]
 */
@Getter
@Setter
@AllArgsConstructor
public class QuestionResult {

    /**
     * Вопрос.
     */
    private String question;

    /**
     * Ответ.
     */
    private String answer;

    /**
     * Количество баллов за ответ.
     */
    private Integer points;

}
