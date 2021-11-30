package dev.struchkov.godfather.context.repository.impl.jpa;

import dev.struchkov.godfather.context.repository.ContentRepository;
import dev.struchkov.godfather.context.repository.jpa.MailRepositoryJpa;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import dev.struchkov.godfather.context.domain.content.Mail;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация репозитория.
 *
 * @author upagge [25/07/2019]
 */
@RequiredArgsConstructor
public class MailRepositoryJpaImpl implements ContentRepository<Mail> {

    private final MailRepositoryJpa mailRepositoryJpa;

    @Override
    public Mail add(@NonNull Mail content) {
        return mailRepositoryJpa.saveAndFlush(content);
    }

    @Override
    public List<Mail> betweenByCreateDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo) {
        return mailRepositoryJpa.findByCreateDateBetween(dateFrom, dateTo);
    }

    @Override
    public List<Mail> betweenByAddDateTime(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo) {
        return mailRepositoryJpa.findByAddDateBetween(dateFrom, dateTo);
    }

    @Override
    public void deleteAllByAddDateBetween(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo) {
        mailRepositoryJpa.deleteAllByAddDateBetween(dateFrom, dateTo);
    }

    @Override
    public void deleteAllByAddDateBefore(@NonNull LocalDateTime date) {
        mailRepositoryJpa.deleteAllByAddDateBefore(date);
    }

    @Override
    public void deleteAllByAddDateAfter(@NonNull LocalDateTime date) {
        mailRepositoryJpa.deleteAllByAddDateAfter(date);
    }

}
