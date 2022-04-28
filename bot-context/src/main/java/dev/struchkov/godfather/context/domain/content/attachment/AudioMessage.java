package dev.struchkov.godfather.context.domain.content.attachment;

import java.net.URL;

/**
 * Вложение типа "Аудиосообщение".
 *
 * @author upagge [08/07/2019]
 */
public class AudioMessage extends Attachment {

    /**
     * Ссылка на аудиозапись в формате odd.
     */
    private URL linkOdd;

    public AudioMessage() {
        type = AttachmentType.AUDIO_MESSAGE;
    }

    public AudioMessage(URL linkOdd) {
        this.linkOdd = linkOdd;
    }

}
