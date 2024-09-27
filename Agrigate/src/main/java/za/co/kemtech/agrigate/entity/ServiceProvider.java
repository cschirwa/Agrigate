package za.co.kemtech.agrigate.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import za.co.kemtech.agrigate.entity.enums.ServiceProviderType;

import java.io.Serializable;
import java.util.UUID;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Entity
@Table(name = "service_provider")
public class ServiceProvider extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid = UUID.randomUUID().toString();
    @Enumerated(EnumType.STRING)
    @Column(name = "service_provider_type")
    private ServiceProviderType serviceProviderType;
    @Column(name = "sp_name")
    private String spName;
    @Column(name = "company_reg")
    private String companyReg;
    @Column(name = "company_vat")
    private String companyVat;
    @Embedded
    private Address address;
    @Embedded
    @Column(name = "contact_name")
    private Contact contact;
    @Column(name = "office_number")
    private String officeNumber;
    @Column(name = "contact_phone")
    private String contactPhone;
    @Column(name = "mobile")
    private String mobile;


    public ServiceProvider(ServiceProviderType serviceProviderType,
                           String spName,
                           String companyReg,
                           String companyVat,
                           Address address,
                           Contact contact,
                           String officeNumber,
                           String contactPhone,
                           String mobile) {
        this.uuid = UUID.randomUUID().toString();
        this.serviceProviderType = serviceProviderType;
        this.spName = spName;
        this.companyReg = companyReg;
        this.companyVat = companyVat;
        this.address = address;
        this.contact = contact;
        this.officeNumber = officeNumber;
        this.contactPhone = contactPhone;
        this.mobile = mobile;
    }
}
