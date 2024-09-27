package za.co.kemtech.agrigate.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class Contact {
    private String firstname;
    private String surname;
    private String phoneNumber;
    @Email
    private String email;
}
