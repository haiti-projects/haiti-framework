package dev.struchkov.godfather.context.service.impl;

import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.repository.ContentRepository;
import dev.struchkov.godfather.context.service.MailService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final ContentRepository<Mail> mailRepository;
    private boolean newMessage = false;
    private LocalDateTime oldDateTime = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault()));

    //TODO [13.04.2022]: Подобная реализация с newMessage вызовет проблемы с несколькими инстансами.
    @Override
    public void add(Mail mail) {
        mailRepository.add(mail);
        newMessage = true;
        log.trace("Сообщение добавлено в репозиторий | {}", mail);
    }

    @Override
    public List<Mail> getByAddDateTime(LocalDateTime timeFrom, LocalDateTime timeTo) {
        log.trace("Запрошены все сообщения {} - {} ", timeFrom, timeTo);
        return mailRepository.betweenByAddDateTime(timeFrom, timeTo);
    }

    @Override
    public List<Mail> getLastEventByCreateDateTime(LocalDateTime timeFrom, LocalDateTime timeTo) {
        log.trace("Запрошены последние сообщения {} - {} ", timeFrom, timeTo);
        final List<Mail> mails = mailRepository.betweenByCreateDateTime(timeFrom, timeTo);
        if (mails != null && !mails.isEmpty()) {
            return findLastMailEachUser(mails);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Mail> getLastEventByAddDateTime(LocalDateTime timeFrom, LocalDateTime timeTo) {
        log.trace("Запрошены последние сообщения {} - {} ", timeFrom, timeTo);
        final List<Mail> mails = mailRepository.betweenByAddDateTime(timeFrom, timeTo);
        if (mails != null && !mails.isEmpty()) {
            return findLastMailEachUser(mails);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Mail> getNewMessage() {
        final LocalDateTime newData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusNanos(999999999);
        if (newMessage) {
            final List<Mail> lastEventByAddDateTime = getLastEventByAddDateTime(oldDateTime, newData);
            newMessage = false;
            oldDateTime = newData;
            return lastEventByAddDateTime;
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteAllByAddDateBetween(@NonNull LocalDateTime dateFrom, @NonNull LocalDateTime dateTo) {
        mailRepository.deleteAllByAddDateBetween(dateFrom, dateTo);
    }

    @Override
    public void deleteAllByAddDateBefore(@NonNull LocalDateTime date) {
        mailRepository.deleteAllByAddDateBefore(date);
    }

    @Override
    public void deleteAllByAddDateAfter(@NonNull LocalDateTime date) {
        mailRepository.deleteAllByAddDateAfter(date);
    }

    /**
     * Возвращает только последнее сообщение каждого пользователя переданного из списка.
     */
    private List<Mail> findLastMailEachUser(List<Mail> mails) {
        final Set<Long> people = new HashSet<>();
        final List<Mail> returnMails = new ArrayList<>();
        for (int i = mails.size() - 1; i >= 0; i--) {
            if (!people.contains(mails.get(i).getPersonId())) {
                returnMails.add(mails.get(i));
                people.add(mails.get(i).getPersonId());
            }
        }
        if (!returnMails.isEmpty()) {
            return returnMails;
        } else {
            return Collections.emptyList();
        }
    }

}
