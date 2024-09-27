package za.co.kemtech.agrigate.exceptions;

public class ServiceProviderNotFoundException extends RuntimeException {
    public ServiceProviderNotFoundException() {
    }

    public ServiceProviderNotFoundException(String msg){
        super(msg);
    }
}
