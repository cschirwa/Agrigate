package za.co.kemtech.agrigate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "country")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "countryIsoCode")
    private String isoCode;
    @Column(name = "countryName")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "currency_id")
    private Currency currency;

}
