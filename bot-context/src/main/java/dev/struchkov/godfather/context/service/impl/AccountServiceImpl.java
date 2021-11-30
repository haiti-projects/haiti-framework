package dev.struchkov.godfather.context.service.impl;

import dev.struchkov.godfather.context.exception.AccessException;
import dev.struchkov.godfather.context.exception.NotFoundException;
import dev.struchkov.godfather.context.repository.AccountRepository;
import dev.struchkov.godfather.context.service.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import dev.struchkov.godfather.context.domain.money.Account;
import dev.struchkov.godfather.context.domain.money.AccountStatus;
import dev.struchkov.godfather.context.exception.PaymentException;

@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account add(@NonNull Account account) {
        if (accountRepository.existsById(account.getId())) {
            account.setAccountStatus(AccountStatus.EXPOSED);
            return accountRepository.save(account);
        } else {
            throw new AccessException("Счет " + account.getId() + " уже присутствует в базе");
        }
    }

    @Override
    public boolean pay(@NonNull Integer accountId, @NonNull Integer extinguishedPersonId, @NonNull Integer sum) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new NotFoundException("Счет " + accountId + " не найден"));
        if (validStatus(account.getAccountStatus())) {
            if (account.getTotalSum().equals(sum)) {
                account.setAccountStatus(AccountStatus.CLOSED);
                account.setExtinguishedPersonId(extinguishedPersonId);
                accountRepository.save(account);
            } else {
                account.setAccountStatus(AccountStatus.EXCEPTION);
                accountRepository.save(account);
                throw new PaymentException("Неверная сумма");
            }
        } else {
            throw new PaymentException("Счет уже оплачен");
        }
        return true;
    }

    private boolean validStatus(@NonNull AccountStatus accountStatus) {
        return AccountStatus.EXCEPTION.equals(accountStatus) || AccountStatus.EXPOSED.equals(accountStatus);
    }

    @Override
    public boolean paymentVerification(@NonNull Integer accountId) {
        return AccountStatus.CLOSED.equals(
                accountRepository.findById(accountId)
                        .orElseThrow(() -> new NotFoundException("Счет " + accountId + " не найден"))
                        .getAccountStatus()
        );
    }

}
