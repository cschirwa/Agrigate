package za.co.kemtech.agrigate.config.auth;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.co.kemtech.agrigate.config.mail.EmailService;
import za.co.kemtech.agrigate.config.mail.EmailTemplateName;
import za.co.kemtech.agrigate.config.security.JwtService;
import za.co.kemtech.agrigate.entity.userdomain.Role;
import za.co.kemtech.agrigate.entity.userdomain.Token;
import za.co.kemtech.agrigate.entity.userdomain.User;
import za.co.kemtech.agrigate.repositories.RoleRepository;
import za.co.kemtech.agrigate.repositories.TokenRepository;
import za.co.kemtech.agrigate.repositories.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Value("${application.mail.frontend.activation-url}")
    private String activationUrl;

    @Value("${application.security.jwt.activation.token.length}")
    private int TOKEN_LENGTH;

    public void register(RegistrationRequest request) throws MessagingException {
        Role userRole = roleRepository.findByName("USER").orElseThrow(
                () -> new IllegalStateException("ROLE USER was not initiated!"));
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .password(passwordEncoder.encode(request.getPassword()))
                .enabled(false)
                .locked(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        String newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getFullname(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Agrigate Account Activation"

        );

    }

    private String generateAndSaveActivationToken(User user) {
        //generate token
        String generatedToken = generateActivationToken(TOKEN_LENGTH);
        Token token = Token.builder()
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(20))
                .user(user)
                .token(generatedToken)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationToken(int length) {
        String chars = "0123456789";

        StringBuilder tokenBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < length ; i++){
            int randomIndex = secureRandom.nextInt(chars.length()); // 0..9
            tokenBuilder.append(chars.charAt(randomIndex));
        }
        log.info("Token generated: " + tokenBuilder.toString());
        return tokenBuilder.toString();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var usernamePasswordAuthToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthToken);
        Map<String, Object> claims = new HashMap<>();
        User user = (User) auth.getPrincipal();
        claims.put("fullName", user.getFullname());
        String jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new RuntimeException("Invalid Token"));
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt())){
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation Token has expired, a new token has been sent");
        }
        User user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(LocalDateTime.now());
        log.info("Token validated");
        tokenRepository.save(savedToken);
    }
}
