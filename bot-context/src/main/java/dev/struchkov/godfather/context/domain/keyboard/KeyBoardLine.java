package dev.struchkov.godfather.context.domain.keyboard;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import dev.struchkov.godfather.context.utils.Description;

import java.util.ArrayList;
import java.util.List;

/**
 * Строка в меню клавиатуры {@link KeyBoard}.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class KeyBoardLine {

    @Description("Кнопки в строке")
    @Singular(value = "buttonKeyBoard")
    private List<KeyBoardButton> keyBoardButtons = new ArrayList<>();

}
