package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.kemtech.agrigate.entity.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long> {

//    Page<Farmer> findAllByName(String name, Pageable pageable);
//    Page<Farmer> findAllById(Long id, Pageable pageable);
}
