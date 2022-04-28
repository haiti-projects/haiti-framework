package dev.struchkov.godfather.context.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Основная сущность для сокрытия id у других сущностей.
 *
 * @author upagge [28/07/2019]
 */
@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
