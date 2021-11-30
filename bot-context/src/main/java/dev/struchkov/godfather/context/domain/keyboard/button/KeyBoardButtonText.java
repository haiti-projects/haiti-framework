package dev.struchkov.godfather.context.domain.keyboard.button;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import dev.struchkov.godfather.context.domain.keyboard.ButtonColor;
import dev.struchkov.godfather.context.domain.keyboard.ButtonType;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import dev.struchkov.godfather.context.utils.Description;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class KeyBoardButtonText extends KeyBoardButton {

    @Description("Надпись на кнопке")
    private String label;

    @Description("Цвет кнопки")
    private ButtonColor color;

    @Builder
    private KeyBoardButtonText(String payload, String label, ButtonColor color) {
        super(payload, ButtonType.TEXT);
        this.label = label;
        this.color = (color != null) ? color : ButtonColor.DEFAULT;
    }

    public static KeyBoardButtonText of(String label) {
        return KeyBoardButtonText.builder().label(label).build();
    }

}
