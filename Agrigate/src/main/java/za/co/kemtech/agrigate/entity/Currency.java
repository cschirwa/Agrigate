package za.co.kemtech.agrigate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@EntityListeners(AuditingEntityListener.class)
@Entity(name = "currency")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Currency extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ccyIsoCode")
    private String isoCode;
    @Column(name = "currencyName")
    private String name;
}
