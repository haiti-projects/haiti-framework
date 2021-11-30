package dev.struchkov.godfather.context.domain.content;

import lombok.Data;
import lombok.EqualsAndHashCode;
import dev.struchkov.godfather.context.domain.BasicEntity;
import dev.struchkov.godfather.context.utils.Description;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Абстрактная сущность - Сообщение от пользователя.
 *
 * @author upagge [08/07/2019]
 */

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class Message extends BasicEntity {

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    @Description("Тип сообщения")
    protected ContentType type;

    @NotNull
    @Column(name = "create_date")
    @Description("Дата создания")
    private LocalDateTime createDate;

    @Column(name = "add_date")
    @Description("Дата добавления в базу")
    private LocalDateTime addDate;

    @NotNull
    @Column(name = "person_id")
    @Description("Идентификатор пользователя, отправившего сообщение")
    private Long personId;

    @Column(name = "text")
    @Description("Текстовое сообщение")
    private String text;

    public Message() {

    }

    public Message(Message source) {
        this.personId = source.getPersonId();
        this.text = source.getText();
        this.createDate = source.getCreateDate();
        this.id = source.getPersonId();
        this.type = source.getType();
    }

}
