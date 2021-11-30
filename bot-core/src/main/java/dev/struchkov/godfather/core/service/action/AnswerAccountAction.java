package dev.struchkov.godfather.core.service.action;

import dev.struchkov.godfather.context.domain.BoxAnswer;
import dev.struchkov.godfather.context.domain.content.Mail;
import dev.struchkov.godfather.context.domain.keyboard.button.KeyBoardButtonAccount;
import dev.struchkov.godfather.context.domain.money.Account;
import dev.struchkov.godfather.context.service.AccountService;
import dev.struchkov.godfather.context.utils.KeyBoards;
import dev.struchkov.godfather.core.domain.AccountAutoCheck;
import dev.struchkov.godfather.core.domain.Timer;
import dev.struchkov.godfather.core.domain.unit.AnswerAccount;
import dev.struchkov.godfather.core.domain.unit.AnswerText;
import dev.struchkov.godfather.core.domain.unit.MainUnit;
import dev.struchkov.godfather.core.service.timer.TimerService;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Обработчик Unit-а {@link AnswerAccount}.
 *
 * @author upagge [11/07/2019]
 */
public class AnswerAccountAction implements ActionUnit<AnswerAccount, Mail> {

    private final AccountService accountService;
    private TimerService timerService;

    public AnswerAccountAction(AccountService accountService, TimerService timerService) {
        this.accountService = accountService;
        this.timerService = timerService;
    }

    public AnswerAccountAction(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public MainUnit action(AnswerAccount answerAccount, Mail mail) {
        final Account account = new Account();
        account.setBelongsPersonId(mail.getPersonId());
        account.setTotalSum(answerAccount.getTotalSum());

        final Integer accountId = accountService.add(account).getId();

        settingCheckTimer(answerAccount, mail, accountId);

        KeyBoardButtonAccount buttonAccount = KeyBoardButtonAccount.builder()
                .accountId(accountId)
                .amount(answerAccount.getTotalSum()).build();

        BoxAnswer boxAnswer = BoxAnswer.builder()
                .message("Для оплаты укажите номер счета " + accountId + "\nСумма к оплате: "
                        + answerAccount.getTotalSum())
                .keyBoard(KeyBoards.singelton(buttonAccount))
                .build();

        return AnswerText.builder().boxAnswer(boxAnswer).build();
    }

    private void settingCheckTimer(AnswerAccount answerAccount, Mail mail, Integer accountId) {
        AccountAutoCheck autoCheck = answerAccount.getAutoCheck();
        if (autoCheck != null && timerService != null) {
            Timer timer = Timer.builder()
                    .personId(mail.getPersonId())
                    .unitAnswer(autoCheck.getSuccessfulPayment())
                    .unitDeath(autoCheck.getFailedPayment())
                    .checkLoop(content1 -> accountService.paymentVerification(accountId))
                    .periodSec(autoCheck.getPeriodSec())
                    .timeActive(LocalDateTime
                            .now(Clock.tickSeconds(ZoneId.systemDefault()))
                            .plusSeconds(autoCheck.getPeriodSec()))
                    .timeDeath(LocalDateTime
                            .now(Clock.tickSeconds(ZoneId.systemDefault()))
                            .plusHours(autoCheck.getLifeTimeHours()))
                    .build();
            timerService.add(timer);
        }
    }
}
