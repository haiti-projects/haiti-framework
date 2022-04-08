package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.domain.content.attachment.Attachment;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Сообщение от пользователя типа "Личное сообщение".
 *
 * @author upagge [08/07/2019]
 */
@Entity
@Getter
@Setter
@Table(name = "mail")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Mail extends Message {

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
    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "attachment")
    private List<Attachment> attachments;

    /**
     * Пересланные сообщения.
     */
    @OneToMany
    @Column(name = "forward_mail")
    private List<Mail> forwardMail;

    public Mail() {
        type = ContentType.MAIL;
    }

}
