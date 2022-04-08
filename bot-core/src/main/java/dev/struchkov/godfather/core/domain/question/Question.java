package dev.struchkov.godfather.core.domain.question;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.List;

/**
 * Используется для конфигурации генерации цепочки Unit-ов в виде тестов для прохождения пользователем.
 *
 * @author upagge [14/07/2019]
 */
@Getter
@Setter
@Builder
public class Question {

    /**
     * Вопрос.
     */
    private BoxAnswer boxAnswer;

    /**
     * Список предполагаемых ответов.
     */
    @Singular
    private List<QuestionAnswer> questionAnswers;

}
