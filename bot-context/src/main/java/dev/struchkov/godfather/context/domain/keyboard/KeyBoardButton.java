package dev.struchkov.godfather.context.domain.keyboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Абстрактная сущность кнопки для клавиатуры.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class KeyBoardButton {

    /**
     * Скрытое сообщение, отправляемое по нажатию.
     */
    protected String payload;

    /**
     * Тип кнопки.
     */
    protected ButtonType type;

}
