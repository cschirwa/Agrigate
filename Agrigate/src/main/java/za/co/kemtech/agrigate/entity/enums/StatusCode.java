package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum StatusCode {
    NEW("new"),
    SCHEDULED("scheduled"),
    INPROGRESS("in-progress"),
    DISPATCHED("dispatched"),
    DELIVERED("delivered"),
    CLOSED("closed"),
    SUSPENDED("suspended"),
    ABANDONED("abandoned");

    private String statusCode;

    StatusCode(String statusCode){
        this.statusCode = statusCode;
    }

}
