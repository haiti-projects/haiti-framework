package dev.struchkov.godfather.main.domain;

import dev.struchkov.godfather.main.domain.keyboard.KeyBoard;

import static dev.struchkov.haiti.utils.Checker.checkNull;

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

    /**
     * <p>Получатель сообщения</p>
     */
    private String recipientPersonId;

    private BoxAnswer(Builder builder) {
        message = builder.message;
        keyBoard = builder.keyBoard;
        replace = builder.replace;
        recipientPersonId = builder.recipientPersonId;
    }

    public static BoxAnswer boxAnswer(boolean replace, String message) {
        return BoxAnswer.builder().replace(replace).message(message).build();
    }

    public static BoxAnswer boxAnswer(boolean replace, String messageText, KeyBoard keyBoard) {
        return BoxAnswer.builder().replace(replace).message(messageText).keyBoard(keyBoard).build();
    }

    public static BoxAnswer boxAnswer(String message) {
        return boxAnswer(false, message);
    }

    public static BoxAnswer boxAnswer(String messageText, KeyBoard keyBoard) {
        return boxAnswer(false, messageText, keyBoard);
    }

    public static BoxAnswer replaceBoxAnswer(String message) {
        return boxAnswer(true, message);
    }

    public static BoxAnswer replaceBoxAnswer(String messageText, KeyBoard keyBoard) {
        return boxAnswer(true, messageText, keyBoard);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder replaceBuilder() {
        return new Builder().replace(true);
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

    public String getRecipientPersonId() {
        return recipientPersonId;
    }

    public void setRecipientPersonId(String recipientPersonId) {
        this.recipientPersonId = recipientPersonId;
    }

    public void setRecipientIfNull(String recipientPersonId) {
        if (checkNull(this.recipientPersonId)) {
            this.recipientPersonId = recipientPersonId;
        }
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
        private String recipientPersonId;

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

        public Builder recipientPersonId(String val) {
            recipientPersonId = val;
            return this;
        }

        public BoxAnswer build() {
            return new BoxAnswer(this);
        }
    }

}
