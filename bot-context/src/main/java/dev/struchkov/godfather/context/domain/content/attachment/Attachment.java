package dev.struchkov.godfather.context.domain.content.attachment;

import dev.struchkov.godfather.context.domain.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Абстрактная сущность, для всех вложений к сообщениям от пользователей.
 *
 * @author upagge [08/07/2019]
 */
@Entity
public abstract class Attachment extends BasicEntity {

    /**
     * Тип сущности.
     */
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    protected AttachmentType type;

    public AttachmentType getType() {
        return type;
    }

}
