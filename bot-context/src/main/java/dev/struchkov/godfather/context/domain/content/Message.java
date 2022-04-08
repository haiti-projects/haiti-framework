package dev.struchkov.godfather.context.domain.content;

import dev.struchkov.godfather.context.domain.BasicEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class Message extends BasicEntity {

    /**
     * Тип сообщения.
     */
    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    protected ContentType type;

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
        this.type = source.getType();
    }

}
