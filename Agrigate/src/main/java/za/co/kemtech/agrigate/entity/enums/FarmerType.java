package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum FarmerType {

    INDIVIDUAL("Individual"),
    CORPORATE("Corporate"),
    COMMERCIAL("Commercial");

    FarmerType(String farmType){}

    private String farmType;

}
