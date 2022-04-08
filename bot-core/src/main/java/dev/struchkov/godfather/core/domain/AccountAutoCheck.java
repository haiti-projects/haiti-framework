package dev.struchkov.godfather.core.domain;

import dev.struchkov.godfather.context.domain.money.Account;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Объект, для автоматической проверки оплаты счета {@link Account}.
 *
 * @author upagge [11/07/2019]
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class AccountAutoCheck {

    /**
     * Unut, который обрабатывается при успешной оплате.
     */
    private MainUnit successfulPayment;

    /**
     * Unit, который обрабатывается при не успешной оплате.
     */
    private MainUnit failedPayment;

    /**
     * Период проверки.
     */
    private Integer periodSec;

    /**
     * Время жизни счета.
     */
    private Integer lifeTimeHours;
}
