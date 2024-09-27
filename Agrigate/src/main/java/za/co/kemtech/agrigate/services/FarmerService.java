package za.co.kemtech.agrigate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.Farmer;
import za.co.kemtech.agrigate.exceptions.FarmerNotFoundExcetpion;
import za.co.kemtech.agrigate.repositories.FarmerRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FarmerService {

    @Autowired
    private final FarmerRepository farmerRepository;

    public Farmer findById(Long farmerId){
        Optional<Farmer> optionalFarmer = farmerRepository.findById(farmerId);
        if (!optionalFarmer.isPresent()){
            throw new FarmerNotFoundExcetpion("Farmer " + farmerId + " Not Found");
        }
        return optionalFarmer.get();
    }

    public Farmer add(Farmer farmer){
        return farmerRepository.save(farmer);
    }
    public Page<Farmer> getAll(){
        return farmerRepository.findAll(PageRequest.of(0, 10));
    }
}
