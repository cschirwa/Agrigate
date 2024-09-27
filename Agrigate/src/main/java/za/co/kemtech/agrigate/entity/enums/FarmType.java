package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum FarmType {
    SMALL("Small < 10H"),
    MEDIUM("Medium < 50H"),
    LARGE("Large > 50H");

    private final String farmType;

    FarmType(String farmType){
        this.farmType = farmType;
    }

}
