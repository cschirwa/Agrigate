package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.kemtech.agrigate.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
