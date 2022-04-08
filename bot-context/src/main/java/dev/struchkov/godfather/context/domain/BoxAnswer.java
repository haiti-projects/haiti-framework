package dev.struchkov.godfather.context.domain;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.content.attachment.GeoCoordinate;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Контейнер, которые содержит данные, которые будут отправлены пользователю как ответ на его запрос.
 *
 * @author upagge [08/07/2019]
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
public class BoxAnswer {

    /**
     * Обычное текстовое сообщение.
     */
    @Setter
    private String message;

    /**
     * Клавиатура - меню.
     */
    private KeyBoard keyBoard;

    /**
     * Географические координаты.
     */
    private GeoCoordinate coordinates;

    /**
     * Идентификатор стикера.
     */
    private Integer stickerId;

    public static BoxAnswer of(String message) {
        return BoxAnswer.builder().message(message).build();
    }

    public static <T extends Message> ProcessingData<T> processing(String messageText) {
        return message -> builder().message(messageText).build();
    }

}
