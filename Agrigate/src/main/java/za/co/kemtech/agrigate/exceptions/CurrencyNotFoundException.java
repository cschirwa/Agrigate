package za.co.kemtech.agrigate.exceptions;

public class CurrencyNotFoundException extends RuntimeException {
    public CurrencyNotFoundException(String msg) {
        super(msg);

    }
}
