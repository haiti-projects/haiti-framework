package dev.struchkov.godfather.context.domain.content;

/**
 * Сообщение от пользователя типа "Комментарий к обсуждению группы".
 *
 * @author upagge [08/07/2019]
 */
public class BoardComment extends Comment {

    public BoardComment() {
        type = ContentType.BOARD_COMMENT;
    }

}
