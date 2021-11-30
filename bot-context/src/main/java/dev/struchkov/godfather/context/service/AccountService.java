package dev.struchkov.godfather.context.service;

import lombok.NonNull;
import dev.struchkov.godfather.context.domain.money.Account;

/**
 * Интерфейс сервиса по работе с оплатой.
 *
 * @author upagge [08/07/2019]
 */
public interface AccountService {

    Account add(@NonNull Account account);

    /**
     * Метод для оплаты счета
     *
     * @param accountId            Идентификатор счета
     * @param extinguishedPersonId Идентификатор пользователя, который внес оплату
     * @param sum                  Сумма оплаты
     * @return true - в случае успешной оплаты
     */
    boolean pay(@NonNull Integer accountId, @NonNull Integer extinguishedPersonId, @NonNull Integer sum);

    /**
     * Проверка оплаты счета
     *
     * @param accountId Идентификатор счета
     * @return true - если счет оплачен
     */
    boolean paymentVerification(@NonNull Integer accountId);

}
