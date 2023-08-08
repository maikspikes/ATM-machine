package nl.dsc.banking.atm.domain;

public interface AccountStorage {
    Account load();
    void save(Account account);
}
