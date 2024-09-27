package za.co.kemtech.agrigate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import za.co.kemtech.agrigate.entity.enums.ActivityType;
import za.co.kemtech.agrigate.entity.enums.FarmType;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "farm")
public class Farm extends AbstractEntity implements Serializable {

    private static final long SerialUID = 1L;

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FARM_PK_SEQ")
//    @SequenceGenerator(name = "FARM_PK_SEQ",sequenceName = "FARM_PK_SEQ", allocationSize = 1, initialValue = 1)
//    @Id
//    @Column(name = "farmId")
//    private Long farmId;
    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();
    @Column(name = "farm_name")
    private String farmName;
    @Column(name = "contact_number")
    private String contactNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    private Farmer farmer;

    @Temporal(value = TemporalType.DATE)
    private Date datePurchased = new Date();

    @Email
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    private FarmType farmType;
    @Embedded
    private Address address;
    @Embedded
    private LatLong gpsLocation;
    private String farmSize;
    private String arableLand;
    private String nonArableLand;
    @Enumerated(EnumType.STRING)
    private ActivityType activityType;


    public Farm(String farmName,
                String contactNumber,
                Farmer farmer,
                Date datePurchased,
                String email,
                FarmType farmType,
                Address address,
                LatLong gpsLocation,
                String farmSize,
                String arableLand,
                String nonArableLand,
                ActivityType activityType) {
        this.uuid = UUID.randomUUID().toString();
        this.farmName = farmName;
        this.contactNumber = contactNumber;
        this.farmer = farmer;
        this.datePurchased = datePurchased;
        this.email = email;
        this.farmType = farmType;
        this.address = address;
        this.gpsLocation = gpsLocation;
        this.farmSize = farmSize;
        this.arableLand = arableLand;
        this.nonArableLand = nonArableLand;
        this.activityType = activityType;
        this.setDateCreated(LocalDateTime.now());
        this.setCreatedBy("System");    //Todo - change after updating security
    }
}
