package dev.struchkov.godfather.context.repository;

import lombok.NonNull;
import dev.struchkov.godfather.context.domain.money.Account;

import java.util.Optional;

/**
 * Репозиторий для взаимодействия с хранилищем счетов {@link Account}.
 *
 * @author upagge [08/07/2019]
 */
public interface AccountRepository {

    Account save(@NonNull Account account);

    Optional<Account> findById(@NonNull Integer accountId);

    boolean existsById(Integer id);

}
