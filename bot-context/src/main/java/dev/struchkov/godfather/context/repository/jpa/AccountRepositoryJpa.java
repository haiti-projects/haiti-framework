package dev.struchkov.godfather.context.repository.jpa;

import dev.struchkov.godfather.context.domain.money.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author upagge [27/07/2019]
 */
@Repository
public interface AccountRepositoryJpa extends JpaRepository<Account, Integer> {

}
