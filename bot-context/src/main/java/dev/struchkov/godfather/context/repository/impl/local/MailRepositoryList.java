package dev.struchkov.godfather.context.repository.impl.local;

import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.repository.ContentRepository;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Локальная реализация репозитория на основе {@link ArrayList} для взаимодействия с сущностью {@link Mail}.
 *
 * @author upagge [27/07/2019]
 */
public class MailRepositoryList implements ContentRepository<Mail> {

    private final List<Mail> mails = new ArrayList<>();
    private Long count = 0L;

    @Override
    public Mail add(Mail mail) {
        mail.setId(count++);
        mails.add(mail);
        return mail;
    }

    @Override
    public List<Mail> betweenByCreateDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo) {
        ArrayList<Mail> rezultMails = new ArrayList<>();
        for (int i = mails.size() - 1; i >= 0; i--) {
            Mail mail = mails.get(i);
            if (isTimePeriod(dateFrom, dateTo, mail.getAddDate())) {
                rezultMails.add(mail);
            } else if (mail.getCreateDate().isBefore(dateFrom)) {
                break;
            }
        }
        return rezultMails;
    }

    @Override
    public List<Mail> betweenByAddDateTime(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo) {
        ArrayList<Mail> rezultMails = new ArrayList<>();
        for (int i = mails.size() - 1; i >= 0; i--) {
            Mail mail = mails.get(i);
            LocalDateTime addDate = mail.getAddDate();
            if (isTimePeriod(dateFrom, dateTo, addDate)) {
                rezultMails.add(mail);
            } else if (addDate.isBefore(dateFrom)) {
                break;
            }
        }
        return rezultMails;
    }

    @Override
    public void deleteAllByAddDateBetween(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo) {
        mails.removeIf(mail -> dateFrom.isBefore(mail.getAddDate()) && dateTo.isAfter(mail.getAddDate()));
    }

    @Override
    public void deleteAllByAddDateBefore(@NotNull LocalDateTime date) {
        mails.removeIf(mail -> date.isBefore(mail.getAddDate()));
    }

    @Override
    public void deleteAllByAddDateAfter(@NotNull LocalDateTime date) {
        mails.removeIf(mail -> date.isAfter(mail.getAddDate()));
    }

    private boolean isTimePeriod(@NotNull LocalDateTime dateFrom, @NotNull LocalDateTime dateTo, @NotNull LocalDateTime dateTime) {
        return dateFrom.isBefore(dateTime) && dateTo.isAfter(dateTime);
    }

}
