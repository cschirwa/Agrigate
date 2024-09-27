package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum ClientType {
    RETAIL("Retailer"),
    WHOLESALER("Wholesaler"),
    INDIVIDUAL("Individual"),
    SPECIALIST("Specialist");

    private final String clientType;
    ClientType(String clientType){
        this.clientType = clientType;
    }

}
