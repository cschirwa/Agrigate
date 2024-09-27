package za.co.kemtech.agrigate.exceptions;

public class FarmerNotFoundExcetpion extends RuntimeException {


    public FarmerNotFoundExcetpion() {
    }

    public FarmerNotFoundExcetpion(String msg){
        super(msg);
    }
}
