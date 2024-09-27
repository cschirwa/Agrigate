package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum ReasonCode {
    ACCEPTED("Accepted"),
    REJECTED("Rejected"),
    SUSPENDED("Suspended");
    private String reasonCode;
    ReasonCode(String reasonCode){
        this.reasonCode = reasonCode;
    }

}
