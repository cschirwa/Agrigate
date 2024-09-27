package za.co.kemtech.agrigate.exceptions;

public class FarmNotFoundException extends RuntimeException{

    public FarmNotFoundException(){
        super();
    }

    public FarmNotFoundException(String msg){
        super(msg);
    }
}
