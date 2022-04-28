package dev.struchkov.godfather.context.domain;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;

/**
 * Контейнер, которые содержит данные, которые будут отправлены пользователю как ответ на его запрос.
 *
 * @author upagge [08/07/2019]
 */
public class BoxAnswer {

    /**
     * Обычное текстовое сообщение.
     */
    private String message;

    /**
     * Клавиатура - меню.
     */
    private KeyBoard keyBoard;

    private boolean replace;

    private BoxAnswer(Builder builder) {
        setMessage(builder.message);
        keyBoard = builder.keyBoard;
        replace = builder.replace;
    }

    public static BoxAnswer of(String message) {
        return BoxAnswer.builder().message(message).build();
    }

    public static <T extends Message> ProcessingData<T> processing(String messageText) {
        return message -> of(messageText);
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    public boolean isReplace() {
        return replace;
    }

    @Override
    public String toString() {
        return "BoxAnswer{" +
                "message='" + message + '\'' +
                ", keyBoard=" + keyBoard +
                ", replace=" + replace +
                '}';
    }


    public static final class Builder {
        private String message;
        private KeyBoard keyBoard;
        private boolean replace;

        private Builder() {
        }

        public Builder message(String val) {
            message = val;
            return this;
        }

        public Builder keyBoard(KeyBoard val) {
            keyBoard = val;
            return this;
        }

        public Builder replace(boolean val) {
            replace = val;
            return this;
        }

        public BoxAnswer build() {
            return new BoxAnswer(this);
        }
    }
}
