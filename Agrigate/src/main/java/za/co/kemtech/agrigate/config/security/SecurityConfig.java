package za.co.kemtech.agrigate.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
import static za.co.kemtech.agrigate.constants.Constants.SPRINGDOC_SECURITY_ENABLED;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
@ConditionalOnProperty(name = SPRINGDOC_SECURITY_ENABLED, havingValue = "true")
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers(
                        "/auth/**",
                                "/login/**",
                                "/login.html",
                                "/index",
                                "/v2/api-docs/",
                                "/v3/api-docs/",
                                "/v3/api-docs/**",
                                "/swagger-resources/",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/static/**",
                                "/css/**",
                                "/js/**",
                                "/vendor/**",
                                "/actuator/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
//                .csrf(csrf -> {
//                    csrf.ignoringRequestMatchers("/login", "/authenticate");
//                })
                .formLogin(form -> form.loginPage("/login"))
                .sessionManagement((s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
