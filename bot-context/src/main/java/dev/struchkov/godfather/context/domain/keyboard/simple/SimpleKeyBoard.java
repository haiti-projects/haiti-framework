package dev.struchkov.godfather.context.domain.keyboard.simple;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardLine;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Сущность клавиатуры, для создания меню с вариантами выбора.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode
public class SimpleKeyBoard implements KeyBoard {

    public static final String TYPE = "SIMPLE";

    /**
     * Строки меню.
     */
    protected List<KeyBoardLine> lines = new ArrayList<>();

    @Builder(builderMethodName = "simpleBuilder", buildMethodName = "simpleBuild")
    public SimpleKeyBoard(@Singular("line") List<KeyBoardLine> lines) {
        this.lines = lines;
    }

    public static SimpleKeyBoard single(KeyBoardLine line) {
        return new SimpleKeyBoard(List.of(line));
    }

    public SimpleKeyBoard single(KeyBoardButton keyBoardButton) {
        return single(SimpleKeyBoardLine.single(keyBoardButton));
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
