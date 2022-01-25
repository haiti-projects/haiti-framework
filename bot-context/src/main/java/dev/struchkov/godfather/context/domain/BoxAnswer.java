package dev.struchkov.godfather.context.domain;

import dev.struchkov.godfather.context.domain.content.Message;
import dev.struchkov.godfather.context.domain.content.attachment.GeoCoordinate;
import dev.struchkov.godfather.context.domain.keyboard.KeyBoard;
import dev.struchkov.godfather.context.service.usercode.ProcessingData;
import dev.struchkov.godfather.context.utils.Description;
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
@EqualsAndHashCode
@ToString
@Getter
@Builder(toBuilder = true)
public class BoxAnswer {

    @Setter
    @Description("Обычное текстовое сообщение")
    private String message;

    @Description("Клавиатура - меню")
    private KeyBoard keyBoard;

    @Description("Географические координаты")
    private GeoCoordinate coordinates;

    @Description("Идентификатор стикера")
    private Integer stickerId;

    public static BoxAnswer of(String message) {
        return BoxAnswer.builder().message(message).build();
    }

    public static <T extends Message> ProcessingData<T> processing(String messageText) {
        return (message) -> builder().message(messageText).build();
    }

}
