package dev.struchkov.godfather.context.domain;

import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;

/**
 * Контейнер, которые содержит данные, которые будут отправлены пользователю как ответ на его запрос.
 *
 * @author upagge [08/07/2019]
 */
public class BoxAnswer {

    /**
     * Клавиатура - меню.
     */
    private final KeyBoard keyBoard;

    /**
     * Флаг означающий, что надо перезаписать наше последнее отправленное сообщение, вместо отправки нового.
     */
    private final boolean replace;

    /**
     * Обычное текстовое сообщение.
     */
    private String message;

    private BoxAnswer(Builder builder) {
        message = builder.message;
        keyBoard = builder.keyBoard;
        replace = builder.replace;
    }

    public static BoxAnswer boxAnswer(String message) {
        return BoxAnswer.builder().message(message).build();
    }

    public static BoxAnswer boxAnswer(String messageText, KeyBoard keyBoard) {
        return BoxAnswer.builder().message(messageText).keyBoard(keyBoard).build();
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
