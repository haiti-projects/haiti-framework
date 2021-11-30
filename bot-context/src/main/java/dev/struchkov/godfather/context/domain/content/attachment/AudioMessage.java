package dev.struchkov.godfather.context.domain.content.attachment;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import dev.struchkov.godfather.context.utils.Description;

import java.net.URL;


/**
 * Вложение типа "Аудиосообщение".
 *
 * @author upagge [08/07/2019]
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
@Setter
public class AudioMessage extends Attachment {

    @Description("Ссылка на аудиозапись в формате odd")
    private URL linkOdd;

    public AudioMessage() {
        type = AttachmentType.AUDIO_MESSAGE;
    }

}
