package dev.struchkov.godfather.context.repository.impl.jpa;

import dev.struchkov.godfather.context.repository.AccountRepository;
import dev.struchkov.godfather.context.repository.jpa.AccountRepositoryJpa;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import dev.struchkov.godfather.context.domain.money.Account;

import java.util.Optional;

/**
 * @author upagge [27/07/2019]
 */
@RequiredArgsConstructor
public class AccountRepositoryJpaImpl implements AccountRepository {

    private final AccountRepositoryJpa accountRepositoryJpa;

    @Override
    public Account save(@NonNull Account account) {
        return accountRepositoryJpa.save(account);
    }

    @Override
    public Optional<Account> findById(@NonNull Integer accountId) {
        return accountRepositoryJpa.findById(accountId);
    }

    @Override
    public boolean existsById(Integer id) {
        return accountRepositoryJpa.existsById(id);
    }

}
