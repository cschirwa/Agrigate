package za.co.kemtech.agrigate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.ServiceProvider;
import za.co.kemtech.agrigate.exceptions.ServiceProviderNotFoundException;
import za.co.kemtech.agrigate.repositories.ServiceProviderRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ServiceProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider findById(Long spId) {
        Optional<ServiceProvider> optionalSP = serviceProviderRepository.findById(spId);
        if (!optionalSP.isPresent()){
            throw new ServiceProviderNotFoundException("SP " + spId + " Not Found");
        }
        return optionalSP.get();
    }

    public ServiceProvider save(ServiceProvider serviceProvider){
        return serviceProviderRepository.save(serviceProvider);
    }
}
