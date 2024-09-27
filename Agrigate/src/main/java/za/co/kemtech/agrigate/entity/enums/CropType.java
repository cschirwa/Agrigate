package za.co.kemtech.agrigate.entity.enums;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.Getter;

@Getter
public enum CropType {
    SEASONAL("Seasonal"),
    SPECIAL("Special"),
    COMMODITY("Commodity");

    private final String cropType;

    CropType(String cropType){
        this.cropType = cropType;
    }

}
