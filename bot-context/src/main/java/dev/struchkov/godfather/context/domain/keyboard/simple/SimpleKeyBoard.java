package dev.struchkov.godfather.context.domain.keyboard.simple;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardLine;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность клавиатуры, для создания меню с вариантами выбора.
 *
 * @author upagge [08/07/2019]
 */
public class SimpleKeyBoard implements KeyBoard {

    public static final String TYPE = "SIMPLE";

    /**
     * Строки меню.
     */
    protected List<KeyBoardLine> lines = new ArrayList<>();

    public SimpleKeyBoard(List<KeyBoardLine> lines) {
        this.lines = lines;
    }

    private SimpleKeyBoard(Builder builder) {
        lines = builder.lines;
    }

    public static SimpleKeyBoard simpleKeyboard(KeyBoardLine line) {
        return new SimpleKeyBoard(List.of(line));
    }

    public static Builder builder() {
        return new Builder();
    }

    public SimpleKeyBoard simpleKeyboard(KeyBoardButton keyBoardButton) {
        return simpleKeyboard(SimpleKeyBoardLine.simpleLine(keyBoardButton));
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public List<KeyBoardLine> getLines() {
        return lines;
    }

    public static final class Builder {
        private List<KeyBoardLine> lines = new ArrayList<>();

        private Builder() {
        }

        public Builder lines(List<KeyBoardLine> val) {
            lines = val;
            return this;
        }

        public Builder line(KeyBoardLine val) {
            lines.add(val);
            return this;
        }

        public SimpleKeyBoard build() {
            return new SimpleKeyBoard(this);
        }
    }
}
