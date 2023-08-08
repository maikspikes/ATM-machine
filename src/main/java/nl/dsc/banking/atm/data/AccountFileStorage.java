package nl.dsc.banking.atm.data;

import nl.dsc.banking.atm.domain.Account;
import nl.dsc.banking.atm.domain.AccountStorage;
import nl.dsc.banking.atm.domain.exception.AccountStorageException;

import java.io.*;
import java.math.BigDecimal;

public class AccountFileStorage implements AccountStorage {
    @Override
    public Account load() {
        BigDecimal checkingBalance = BigDecimal.ZERO;
        BigDecimal savingBalance = BigDecimal.ZERO;

        try (BufferedReader reader = new BufferedReader(new FileReader("src/AccountBalance.txt"))){
            String line;
            while ((line = reader.readLine()) != null) {
                String [] parts = line.split("=");
                if (parts.length == 2) {
                    String balanceType = parts[0];
                    String balanceStr = parts[1];
                    BigDecimal balance = new BigDecimal(balanceStr);
                    if (balanceType.equals("CheckingBalance")) {
                        checkingBalance = balance;
                    } else if (balanceType.equals("SavingsBalance")) {
                        savingBalance = balance;
                    }
                }
            }

            return new Account(checkingBalance, savingBalance);
        } catch (IOException e) {
            throw new AccountStorageException("Cannot load account from file", e);
        }
    }

    @Override
    public void save(Account account) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/AccountBalance.txt"))){
            writer.write("CheckingBalance=" + account.getCheckingBalance());
            writer.newLine();
            writer.write("SavingsBalance=" + account.getSavingBalance());
        } catch (IOException e) {
            throw new AccountStorageException("Cannot save account from file", e);
        }
    }
}





