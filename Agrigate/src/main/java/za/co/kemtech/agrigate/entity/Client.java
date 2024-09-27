package za.co.kemtech.agrigate.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import za.co.kemtech.agrigate.entity.enums.ClientType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "client")
public class Client extends AbstractEntity implements Serializable{

    private static final long serialUID = 1L;

//    @Id
//    @Column(name = "client_id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENT_PK_SEQ")
//    @SequenceGenerator(name = "CLIENT_PK_SEQ",sequenceName = "CLIENT_PK_SEQ", allocationSize = 1, initialValue = 1)
//    private Long clientId;
    private String uuid = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    @Column(name = "clienttype")
    private ClientType clientType;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "reg_number")
    private String regNumber;
    @Column(name = "vat_number")
    private String vatNumber;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @Column(name = "office_number")
    private String officeNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Email
    @Column(name = "client_email")
    private String email;

    public Client(String uuid,
                  ClientType clientType,
                  String clientName,
                  String regNumber,
                  String vatNumber,
                  Address address,
                  Contact contact,
                  String officeNumber,
                  String mobileNumber,
                  String email) {
        this.uuid = uuid;
        this.clientType = clientType;
        this.clientName = clientName;
        this.regNumber = regNumber;
        this.vatNumber = vatNumber;
        this.address = address;
        this.contact = contact;
        this.officeNumber = officeNumber;
        this.mobileNumber = mobileNumber;
        this.email = email;
//        this.setDateCreated(LocalDateTime.now());
//        this.setCreatedBy("System"); //Todo - change after updating security
    }
}
