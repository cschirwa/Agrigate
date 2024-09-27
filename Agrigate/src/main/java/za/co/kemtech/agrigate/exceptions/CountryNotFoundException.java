package za.co.kemtech.agrigate.exceptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String msg) {
        super(msg);
    }
}
