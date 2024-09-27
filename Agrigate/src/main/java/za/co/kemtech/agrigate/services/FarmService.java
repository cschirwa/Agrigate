package za.co.kemtech.agrigate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.Farm;
import za.co.kemtech.agrigate.exceptions.FarmNotFoundException;
import za.co.kemtech.agrigate.repositories.FarmRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FarmService {

    @Autowired
    private final FarmRepository farmRepository;

    public Farm findById(final Long farmId){
        Optional<Farm> optionalFarm = farmRepository.findById(farmId);
        if(!optionalFarm.isPresent()){
            throw new FarmNotFoundException("Farm " + farmId + " not found on database");
        }
        return optionalFarm.get();
    }

    public Farm add(final Farm farm){
        return farmRepository.save(farm);
    }
}
