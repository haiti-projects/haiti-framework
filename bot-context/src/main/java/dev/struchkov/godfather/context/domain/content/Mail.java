package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.domain.content.attachment.Attachment;
import dev.struchkov.godfather.context.domain.event.Event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Сообщение от пользователя типа "Личное сообщение".
 *
 * @author upagge [08/07/2019]
 */
@Entity
@Table(name = "mail")
public class Mail extends Message implements Event {

    public static final String TYPE = "MAIL";

    /**
     * Имя отправителя.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Фамилия отправителя.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Вложения к сообщению.
     */
    @Column(name = "attachment")
    @OneToMany(fetch = FetchType.LAZY)
    private List<Attachment> attachments = new ArrayList<>();

    /**
     * Пересланные сообщения.
     */
    @OneToMany
    @Column(name = "forward_mail")
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
    public String getType() {
        return TYPE;
    }

}
