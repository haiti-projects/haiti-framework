package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.domain.BasicEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Абстрактная сущность - Сообщение от пользователя.
 *
 * @author upagge [08/07/2019]
 */
@MappedSuperclass
public abstract class Message extends BasicEntity {

    /**
     * Тип сообщения.
     */
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    protected ContentType contentType;

    /**
     * Дата создания.
     */
    @NotNull
    @Column(name = "create_date")
    private LocalDateTime createDate;

    /**
     * Дата добавления в базу.
     */
    @Column(name = "add_date")
    private LocalDateTime addDate;

    /**
     * Идентификатор пользователя, отправившего сообщение.
     */
    @NotNull
    @Column(name = "person_id")
    private Long personId;

    /**
     * Текстовое сообщение.
     */
    @Column(name = "text")
    private String text;

    public Message(Message source) {
        this.personId = source.getPersonId();
        this.text = source.getText();
        this.createDate = source.getCreateDate();
        this.id = source.getPersonId();
        this.contentType = source.getContentType();
    }

    public Message() {
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

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
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
        return contentType == message.contentType && Objects.equals(createDate, message.createDate) && Objects.equals(addDate, message.addDate) && Objects.equals(personId, message.personId) && Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentType, createDate, addDate, personId, text);
    }

}
