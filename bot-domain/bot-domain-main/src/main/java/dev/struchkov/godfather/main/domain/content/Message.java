package dev.struchkov.godfather.main.domain.content;

import dev.struchkov.autoresponder.entity.DeliverableText;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Абстрактная сущность - Сообщение от пользователя.
 *
 * @author upagge [08/07/2019]
 */
public abstract class Message implements DeliverableText {

    /**
     * Тип сообщения.
     */
    protected ContentType contentType;

    /**
     * Дата создания.
     */
    private LocalDateTime createDate;

    /**
     * Идентификатор пользователя, отправившего сообщение.
     */
    private String personId;

    /**
     * Текстовое сообщение.
     */
    private String text;

    protected Message(Message source) {
        this.personId = source.getPersonId();
        this.text = source.getText();
        this.createDate = source.getCreateDate();
        this.contentType = source.getContentType();
    }

    protected Message() {
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType type) {
        this.contentType = type;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return contentType == message.contentType && Objects.equals(createDate, message.createDate) && Objects.equals(personId, message.personId) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, createDate, personId, text);
    }
}
