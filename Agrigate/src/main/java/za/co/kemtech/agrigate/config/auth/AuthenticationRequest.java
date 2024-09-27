package za.co.kemtech.agrigate.config.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @NotEmpty(message = "username is mandatory")
    @NotNull(message = "username is mandatory")
    private String username;
    @NotEmpty(message = "username is mandatory")
    @NotNull(message = "username is mandatory")
    @Size(min = 6, message = "Password min size is 6")
    private String password;
}
