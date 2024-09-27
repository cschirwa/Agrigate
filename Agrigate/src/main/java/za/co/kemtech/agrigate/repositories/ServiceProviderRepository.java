package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.kemtech.agrigate.entity.ServiceProvider;

public interface ServiceProviderRepository extends JpaRepository<ServiceProvider, Long> {
}
