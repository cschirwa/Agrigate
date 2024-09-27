package za.co.kemtech.agrigate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import za.co.kemtech.agrigate.entity.enums.FarmerType;
import za.co.kemtech.agrigate.entity.enums.IdDocumentType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "farmer")
//@Table(name = "farmer")
public class Farmer extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FARMER_PK_SEQ")
//    @SequenceGenerator(name = "FARMER_PK_SEQ",sequenceName = "FARMER_PK_SEQ", allocationSize = 1, initialValue = 1)
//    @Id
//    @Column(name = "farmerId")
//    private Long id;
    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "surname")
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(name = "idtype")
    private IdDocumentType idDocumentType;

    @Column(name = "idnumber")
    private String idNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "farmertype")
    private FarmerType farmerType;

    @Column(name = "contactnumber")
    private String contactNumber;
    @Column(name = "mobilenumber")
    private String mobileNumber;
    @Embedded
    private Address address;
    @Email
    @Column(name = "email")
    private String email;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    private List<Farm> farms;

    public Farmer(String firstName,
                  String surname,
                  IdDocumentType idDocumentType,
                  String idNumber,
                  FarmerType farmerType,
                  String contactNumber,
                  String mobileNumber,
                  Address address,
                  String email) {
        this.uuid = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.surname = surname;
        this.idDocumentType = idDocumentType;
        this.idNumber = idNumber;
        this.farmerType = farmerType;
        this.contactNumber = contactNumber;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.setDateCreated(LocalDateTime.now());
        this.setCreatedBy("System");    //Todo - change after updating security
    }
}
