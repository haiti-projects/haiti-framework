package dev.struchkov.godfather.context.repository.jpa;

import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.repository.impl.jpa.MailRepositoryJpaImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс JPA репозитория для сущности {@link Mail}. При этом сам не является репозиторием, а подставляется в
 * {@link MailRepositoryJpaImpl}
 *
 * @author upagge [27/07/2019]
 */
@Repository
public interface MailRepositoryJpa extends JpaRepository<Mail, Integer> {

    List<Mail> findByCreateDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Mail> findByAddDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);

    void deleteAllByAddDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);

    void deleteAllByAddDateBefore(LocalDateTime date);

    void deleteAllByAddDateAfter(LocalDateTime date);

}
