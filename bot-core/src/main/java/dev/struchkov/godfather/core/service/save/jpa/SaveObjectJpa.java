package dev.struchkov.godfather.core.service.save.jpa;

import dev.struchkov.godfather.context.domain.BasicEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * TODO: Добавить описание класса.
 *
 * @author upagge [01/08/2019]
 */
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class SaveObjectJpa extends BasicEntity {

    @Column(name = "personId")
    private Long personId;

}
