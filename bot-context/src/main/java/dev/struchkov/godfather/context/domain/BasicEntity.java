package dev.struchkov.godfather.context.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Основная сущность для сокрытия id у других сущностей.
 *
 * @author upagge [28/07/2019]
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue
    protected Long id;

}
