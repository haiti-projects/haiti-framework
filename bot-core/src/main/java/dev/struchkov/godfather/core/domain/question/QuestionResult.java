package dev.struchkov.godfather.core.domain.question;

import dev.struchkov.godfather.context.utils.Description;
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

    @Description("Вопрос")
    private String question;

    @Description("Ответ")
    private String answer;

    @Description("Количество баллов за ответ")
    private Integer points;

}
