package nl.dsc.banking.atm.domain.exception;

public class AccountStorageException extends RuntimeException {
    public AccountStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
