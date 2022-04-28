package dev.struchkov.godfather.context.domain.keyboard.button;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Абстрактная сущность кнопки для клавиатуры.
 *
 * @author upagge [08/07/2019]
 */
public class SimpleButton implements KeyBoardButton {

    public static final String TYPE = "SIMPLE";

    /**
     * Надпись на кнопке.
     */
    protected String label;
    /**
     * Данные, которые возвращаются при нажатии.
     */
    protected String callbackData;

    protected SimpleButton(String label, String callbackData) {
        this.label = label;
        this.callbackData = callbackData;
    }

    public static SimpleButton of(@NotNull String label, @NotNull String callbackData) {
        return new SimpleButton(label, callbackData);
    }

    public static SimpleButton of(@NotNull String label) {
        return new SimpleButton(label, label);
    }

    public String getLabel() {
        return label;
    }

    public String getCallbackData() {
        return callbackData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleButton that = (SimpleButton) o;
        return Objects.equals(label, that.label) && Objects.equals(callbackData, that.callbackData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, callbackData);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
