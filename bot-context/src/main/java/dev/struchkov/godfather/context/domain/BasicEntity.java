package dev.struchkov.godfather.context.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Основная сущность для сокрытия id у других сущностей.
 *
 * @author upagge [28/07/2019]
 */
@Data
@ToString
@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue
    protected Long id;

}
