package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.kemtech.agrigate.entity.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

    Optional<Currency> findByIsoCode(String isoCode);
//    Optional<Currency> findByName(String name);
}
