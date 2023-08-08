package nl.dsc.banking.atm.presentation;

import nl.dsc.banking.atm.application.ATMService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ATMCLI {
    private final ATMService atmService;
    private boolean isRunning = true;

    public ATMCLI(ATMService atmService) {
        this.atmService = atmService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);


        while (isRunning) {
            listOptions();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showCheckingBalance();
                case 2 -> showSavingsBalance();
                case 3 -> depositAmount(scanner);
                case 4 -> withdrawAmount(scanner);
                case 5 -> depositFundsToSavings(scanner);
                case 6 -> withdrawFundsFromSavings(scanner);
                case 7 -> exit();
                default -> System.out.println("Invalid choice");
            }
        }

    }

    private void withdrawFundsFromSavings(Scanner scanner) {
        System.out.println("withdrawing funds from savings account to checking account");

        BigDecimal amount = scanner.nextBigDecimal();

        BigDecimal result = atmService.withdrawSavingsBalance(amount);

        System.out.println(result);

    }

    private void depositFundsToSavings(Scanner scanner) {
        System.out.println("Depositing funds from checking account to savings account");

        BigDecimal amount = scanner.nextBigDecimal();

        BigDecimal result = atmService.depositSavingsBalance(amount);

        System.out.println(result);
    }


    private void listOptions() {
        System.out.println("""
                here are the options:
                press 1 to see checking balance
                press 2 to see savings balance
                press 3 to deposit to checking balance
                press 4 to withdraw from checking balance
                press 5 to deposit to savings balance
                press 6 to withdraw from savings balance
                Press 7 to exit the ATM \n""");
    }

    private void withdrawAmount(Scanner scanner) {
        System.out.println("withdraw from checking balance");
        BigDecimal amount = scanner.nextBigDecimal();

        BigDecimal result = atmService.withdrawCheckingBalance(amount);

        System.out.println(result);
    }
    private void depositAmount(Scanner scanner) {
        System.out.println("Deposit to checking balance");
        BigDecimal amount = scanner.nextBigDecimal();

        BigDecimal result = atmService.depositCheckingBalance(amount);

        System.out.println(result);
    }
    private void showSavingsBalance() {
        BigDecimal savingBalance = this.atmService.showSavingBalance();
        System.out.println("SavingsBalance: " + savingBalance);
    }
    private void showCheckingBalance() {
        BigDecimal checkingBalance = this.atmService.showCheckingBalance();
        System.out.println("Checking balance: " + checkingBalance);
    }

    private void exit() {
        isRunning = false;
        System.out.println("Have a good day.");
    }

}