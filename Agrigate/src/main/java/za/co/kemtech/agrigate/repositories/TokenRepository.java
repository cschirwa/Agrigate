package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.kemtech.agrigate.entity.userdomain.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findByToken(String token);
}
