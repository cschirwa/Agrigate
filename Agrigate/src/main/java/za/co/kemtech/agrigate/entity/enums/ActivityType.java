package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum ActivityType {
    PRODUCE("produce"),
    ANIMAL_HUSBANDRY("animal husbandry");

    private final String activityType;

    ActivityType(String activityType){
        this.activityType = activityType;
    }

}
