package dev.struchkov.godfather.context.domain.keyboard.simple;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardLine;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Строка в меню клавиатуры {@link SimpleKeyBoard}.
 *
 * @author upagge [08/07/2019]
 */
@ToString
@EqualsAndHashCode
public class SimpleKeyBoardLine implements KeyBoardLine {

    /**
     * Кнопки в строке.
     */
    protected List<KeyBoardButton> buttons = new ArrayList<>();

    @Builder(builderMethodName = "simpleBuilder", buildMethodName = "simpleBuild")
    public SimpleKeyBoardLine(@Singular(value = "button") List<KeyBoardButton> buttons) {
        this.buttons = buttons;
    }

    public static SimpleKeyBoardLine single(KeyBoardButton keyBoardButton) {
        return new SimpleKeyBoardLine(List.of(keyBoardButton));
    }

    @Override
    public List<KeyBoardButton> getButtons() {
        return buttons;
    }

}
