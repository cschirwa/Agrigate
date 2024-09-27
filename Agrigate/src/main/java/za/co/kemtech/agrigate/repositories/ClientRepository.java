package za.co.kemtech.agrigate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.kemtech.agrigate.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

//    Page<Client> findAllByName(String name, Pageable pageable);
//    Page<Client> findAllById(Long id, Pageable pageable);

}
