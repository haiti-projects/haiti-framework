package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.utils.Description;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Абстрактная сущность для сообщений от пользователей с привязкой к какому-то контенту (картинка, видео).
 *
 * @author upagge [08/07/2019]
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class Comment extends Message {

    @Description("Идентификатор контента, к которому ставлено сообщение")
    private Long contentId;

}
