package za.co.kemtech.agrigate.entity.enums;

import lombok.Getter;

@Getter
public enum IdDocumentType {
    ID("National_Id"),
    PASSPORT("Passport"),
    COMPANY_REG("Company_Registration");

    private String IdType;
    IdDocumentType(String IdType){

    }

    public String getIdTpe(){
        return this.IdType;
    }

}
