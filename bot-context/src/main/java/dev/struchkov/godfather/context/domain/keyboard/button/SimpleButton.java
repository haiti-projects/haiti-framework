package dev.struchkov.godfather.context.domain.keyboard.button;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoardButton;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Абстрактная сущность кнопки для клавиатуры.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode
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

    @Builder(builderMethodName = "simpleBuilder", buildMethodName = "simpleBuild")
    protected SimpleButton(String label, String callbackData) {
        this.label = label;
        this.callbackData = callbackData;
    }

    public static SimpleButton of(@NonNull String label, @NonNull String callbackData) {
        return new SimpleButton(label, callbackData);
    }

    public static SimpleButton of(@NonNull String label) {
        return new SimpleButton(label, label);
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
