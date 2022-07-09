package dev.struchkov.godfather.context.domain.content.attachment;

import dev.struchkov.godfather.context.domain.BasicEntity;

import javax.persistence.Entity;

/**
 * Абстрактная сущность, для всех вложений к сообщениям от пользователей.
 *
 * @author upagge [08/07/2019]
 */
@Entity
public abstract class Attachment extends BasicEntity {

    public abstract String getType();

}
