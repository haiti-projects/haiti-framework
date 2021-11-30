package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.domain.content.attachment.Attachment;
import dev.struchkov.godfather.context.utils.Description;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Data
@Entity
@Table(name = "mail")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Mail extends Message {

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "attachment")
    @Description("Вложения к сообщению")
    private List<Attachment> attachments;

    @OneToMany
    @Column(name = "forward_mail")
    @Description("Пересланные сообщения")
    private List<Mail> forwardMail;

    public Mail() {
        type = ContentType.MAIL;
    }

}
