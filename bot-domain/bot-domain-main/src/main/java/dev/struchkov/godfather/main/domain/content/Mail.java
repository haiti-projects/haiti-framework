package dev.struchkov.godfather.main.domain.content;

import dev.struchkov.godfather.main.domain.event.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Сообщение от пользователя типа "Личное сообщение".
 *
 * @author upagge [08/07/2019]
 */

public class Mail extends Message implements Event {

    public static final String TYPE = "MAIL";

    /**
     * Имя отправителя.
     */
    private String firstName;

    /**
     * Фамилия отправителя.
     */
    private String lastName;

    /**
     * Вложения к сообщению.
     */
    private List<Attachment> attachments = new ArrayList<>();

    /**
     * Пересланные сообщения.
     */
    private List<Mail> forwardMail;

    public Mail() {
        contentType = ContentType.MAIL;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public void addAttachments(Collection<Attachment> attachments) {
        this.attachments.addAll(attachments);
    }

    public List<Mail> getForwardMail() {
        return forwardMail;
    }

    public void setForwardMail(List<Mail> forwardMail) {
        this.forwardMail = forwardMail;
    }

    @Override
    public String getEventType() {
        return TYPE;
    }

}
