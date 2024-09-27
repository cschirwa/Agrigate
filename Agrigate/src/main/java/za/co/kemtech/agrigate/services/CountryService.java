package za.co.kemtech.agrigate.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.Country;
import za.co.kemtech.agrigate.exceptions.CountryNotFoundException;
import za.co.kemtech.agrigate.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Country findByName(String countryName){
        Optional<Country> countryOptional = countryRepository.findCountryByName(countryName);
        if (countryOptional.isPresent()){
            return countryOptional.get();
        }
        throw new CountryNotFoundException("country " + countryName + " Not Found");
    }

    public List<Country> findAllCountriesSorted(){
        return countryRepository.findAll(Sort.by("name").ascending());
    }

    public Country add(Country country){
        return countryRepository.save(country);
    }
}
