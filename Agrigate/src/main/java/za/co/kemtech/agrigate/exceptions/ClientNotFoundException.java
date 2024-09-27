package za.co.kemtech.agrigate.exceptions;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException(){}
    public ClientNotFoundException(String s) {
        super(s);
    }
}
