package dev.struchkov.godfather.context.service.impl;

import dev.struchkov.godfather.context.repository.ContentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.service.MailService;

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
        List<Mail> mails = mailRepository.betweenByCreateDateTime(timeFrom, timeTo);
        if (mails != null && !mails.isEmpty()) {
            return getReturnMails(mails);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Mail> getLastEventByAddDateTime(LocalDateTime timeFrom, LocalDateTime timeTo) {
        log.trace("Запрошены последние сообщения {} - {} ", timeFrom, timeTo);
        List<Mail> mails = mailRepository.betweenByAddDateTime(timeFrom, timeTo);
        if (mails != null && !mails.isEmpty()) {
            return getReturnMails(mails);
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Mail> getNewMessage() {
        LocalDateTime newData = LocalDateTime.now(Clock.tickSeconds(ZoneId.systemDefault())).plusNanos(999999999);
        List<Mail> lastEventByAddDateTime = Collections.emptyList();
        if (newMessage) {
            lastEventByAddDateTime = getLastEventByAddDateTime(oldDateTime, newData);
            newMessage = false;
            oldDateTime = newData;
        }
        return lastEventByAddDateTime;
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

    private List<Mail> getReturnMails(List<Mail> mails) {
        Set<Long> people = new HashSet<>();
        List<Mail> returnMails = new ArrayList<>();
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
