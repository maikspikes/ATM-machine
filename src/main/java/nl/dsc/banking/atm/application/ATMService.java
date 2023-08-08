package nl.dsc.banking.atm.application;

import nl.dsc.banking.atm.domain.Account;
import nl.dsc.banking.atm.domain.AccountStorage;

import java.math.BigDecimal;

public class ATMService {
    private final AccountStorage accountStorage;

    public ATMService(AccountStorage accountStorage) {
        this.accountStorage = accountStorage;
    }

    public BigDecimal showCheckingBalance() {
        Account account = this.accountStorage.load();

        return account.getCheckingBalance();
    }

    public BigDecimal showSavingBalance() {
        Account account = this.accountStorage.load();

        return account.getSavingBalance();
    }

    public BigDecimal depositCheckingBalance(BigDecimal amount) {
       Account account = this.accountStorage.load();

        account.depositCheckingBalance(amount);

        this.accountStorage.save(account);

        return account.getCheckingBalance();
    }

    public BigDecimal withdrawCheckingBalance(BigDecimal amount) {
        Account account = this.accountStorage.load();

        account.withdrawCheckingBalance(amount);

        this.accountStorage.save(account);

        return account.getCheckingBalance();
    }

    public BigDecimal depositSavingsBalance(BigDecimal amount) {
        Account account = this.accountStorage.load();

        account.depositSavingsBalance(amount);

        this.accountStorage.save(account);

        return account.getSavingBalance();
    }

    public BigDecimal withdrawSavingsBalance(BigDecimal amount) {
        Account account = this.accountStorage.load();

        account.withdrawSavingsBalance(amount);

        this.accountStorage.save(account);

        return account.getSavingBalance();
    }
}