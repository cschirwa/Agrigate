package za.co.kemtech.agrigate.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Embeddable
@NoArgsConstructor
public class LatLong {
    private Double latitude, longitude;
}
