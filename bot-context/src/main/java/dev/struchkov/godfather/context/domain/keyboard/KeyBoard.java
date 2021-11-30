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
 * Сущность клавиатуры, для создания меню с вариантами выбора.
 *
 * @author upagge [08/07/2019]
 */
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class KeyBoard {

    @Description("Строки меню")
    @Singular(value = "lineKeyBoard")
    private List<KeyBoardLine> keyBoardLines = new ArrayList<>();

    @Description("Скрыть меню после ответа или нет")
    private boolean oneTime = true;

}
