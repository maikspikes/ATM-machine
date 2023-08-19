package nl.dsc.banking.atm.domain;

import nl.dsc.banking.atm.domain.exception.InvalidOperationException;

import java.math.BigDecimal;

public class Account {
    private BigDecimal checkingBalance;
    private BigDecimal savingBalance;

    public Account(BigDecimal checkingBalance, BigDecimal savingBalance) {
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
    }

    public BigDecimal getCheckingBalance() {
        return checkingBalance;
    }

    public BigDecimal getSavingBalance() {
        return savingBalance;
    }

    public void depositCheckingBalance(BigDecimal amount) {
        if (isLessThanZero(amount)) {
            throw new InvalidOperationException("Cannot deposit a negative amount to checking balance");
        } else {
            this.checkingBalance = this.checkingBalance.add(amount);
        }
    }

    public void depositSavingsBalance(BigDecimal amount){
        if (isGreaterThanZero(amount)) {
            throw new InvalidOperationException("Cannot deposit a negative amount to savings balance");
        }

        this.savingBalance = this.savingBalance.add(amount);

    }

    public void withdrawCheckingBalance(BigDecimal amount) {
        if (isLessThanZero(amount)) {
            throw new InvalidOperationException("Cannot withdraw a negative amount from checking balance");
        }

        this.checkingBalance = this.checkingBalance.subtract(amount);
    }

    public void withdrawSavingsBalance(BigDecimal amount){
        if (isLessThanZero(amount)) {
            throw new InvalidOperationException("Cannot withdraw a negative amount from savings balance");
        }

        this.savingBalance = this.savingBalance.subtract(amount);
    }




    private static boolean isGreaterThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
    private static boolean isLessThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

}
