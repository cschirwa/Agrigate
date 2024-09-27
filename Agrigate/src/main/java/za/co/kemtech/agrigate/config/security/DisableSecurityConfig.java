package za.co.kemtech.agrigate.config.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static za.co.kemtech.agrigate.constants.Constants.SPRINGDOC_SECURITY_ENABLED;

@Configuration
@ConditionalOnProperty(name = SPRINGDOC_SECURITY_ENABLED, havingValue = "false")
public class DisableSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> req.anyRequest().permitAll())
                .build();
    }
}
