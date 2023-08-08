package nl.dsc.banking;

import nl.dsc.banking.atm.application.ATMService;
import nl.dsc.banking.atm.data.AccountFileStorage;
import nl.dsc.banking.atm.domain.AccountStorage;
import nl.dsc.banking.atm.presentation.ATMCLI;

public class BankingApplication {
    public static void main(String[] args) {
        // Dependency injection (manual constructor injection)
        AccountStorage accountStorage = new AccountFileStorage();
        ATMService atmService = new ATMService(accountStorage);
        ATMCLI atmCli = new ATMCLI(atmService);

        atmCli.start();
    }
}
