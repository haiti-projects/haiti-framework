package dev.struchkov.godfather.context.domain.money;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Сущность, которая отвечает за выставленный пользователю счет.
 *
 * @author upagge [08/07/2019]
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * Сумма к оплате.
     */
    @NotNull
    @Column(name = "totalSum")
    private Integer totalSum;

    /**
     * Идентификатор пользователя, которому выставлен счет.
     */
    @Column(name = "belongs_person_id")
    private Long belongsPersonId;

    /**
     * Идентификатор пользователя, который оплатил счет.
     */
    @Column(name = "extinguished_person_id")
    private Integer extinguishedPersonId;

    /**
     * Описание платежа.
     */
    @Column(name = "description")
    private String description;

    /**
     * Статус оплаты счета.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus accountStatus;

}
