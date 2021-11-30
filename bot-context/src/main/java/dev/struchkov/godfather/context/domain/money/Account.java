package dev.struchkov.godfather.context.domain.money;

import dev.struchkov.godfather.context.utils.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    @Description("Идентификатор счета")
    private Integer id;

    @NotNull
    @Column(name = "totalSum")
    @Description("Сумма к оплате")
    private Integer totalSum;

    @Column(name = "belongs_person_id")
    @Description("Идентификатор пользователя, которому выставлен счет")
    private Long belongsPersonId;

    @Column(name = "extinguished_person_id")
    @Description("Идентификатор пользователя, который оплатил счет")
    private Integer extinguishedPersonId;

    @Column(name = "description")
    @Description("Описание платежа")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Description("Статус оплаты счета")
    private AccountStatus accountStatus;

}
