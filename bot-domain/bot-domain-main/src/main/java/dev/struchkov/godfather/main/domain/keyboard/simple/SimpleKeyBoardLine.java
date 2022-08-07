package dev.struchkov.godfather.main.domain.keyboard.simple;

import dev.struchkov.godfather.main.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.main.domain.keyboard.KeyBoardLine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Строка в меню клавиатуры {@link SimpleKeyBoard}.
 *
 * @author upagge [08/07/2019]
 */
public class SimpleKeyBoardLine implements KeyBoardLine {

    /**
     * Кнопки в строке.
     */
    protected List<KeyBoardButton> buttons;

    public SimpleKeyBoardLine(List<KeyBoardButton> buttons) {
        this.buttons = buttons;
    }

    private SimpleKeyBoardLine(Builder builder) {
        buttons = builder.buttons;
    }

    public static SimpleKeyBoardLine simpleLine(KeyBoardButton... keyBoardButton) {
        return new SimpleKeyBoardLine(Arrays.stream(keyBoardButton).toList());
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public List<KeyBoardButton> getButtons() {
        return buttons;
    }

    public static final class Builder {
        private List<KeyBoardButton> buttons = new ArrayList<>();

        private Builder() {
        }

        public Builder buttons(List<KeyBoardButton> val) {
            buttons = val;
            return this;
        }

        public Builder button(KeyBoardButton val) {
            buttons.add(val);
            return this;
        }

        public SimpleKeyBoardLine build() {
            return new SimpleKeyBoardLine(this);
        }
    }
}
