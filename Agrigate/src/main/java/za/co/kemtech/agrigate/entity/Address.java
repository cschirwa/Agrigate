package za.co.kemtech.agrigate.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class Address {
    private String line1;
    private String line2;
    private String city;
    private int postcode;
    private String province;
    private String country;
    public Address(String line1,
                   String line2,
                   String city,
                   int postcode,
                   String province,
                   String country){
        super();
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.postcode = postcode;
        this.province = province;
        this.country = country;
    }
}
