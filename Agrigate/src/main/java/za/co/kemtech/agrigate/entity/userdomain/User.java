package za.co.kemtech.agrigate.entity.userdomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import za.co.kemtech.agrigate.entity.AbstractEntity;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "_user")
@EntityListeners(AuditingEntityListener.class)
public class User extends AbstractEntity implements Serializable, UserDetails, Principal {

    private static final long serialUID = 1L;

    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonIgnore
    @Column(name = "password")
    private String password;
    @Email
    @Column(name = "email", unique = true)
    private String email;
    private int failedLogins;
    private boolean locked;
    private boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lockedDate;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLoginTime;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public void updatePassword(final String password){

    }

    @Override
    public String getName() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullname(){
        return firstName + " " + lastName;
    }
}
