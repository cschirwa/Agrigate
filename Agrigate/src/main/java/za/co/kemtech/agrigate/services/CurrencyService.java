package za.co.kemtech.agrigate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.kemtech.agrigate.entity.Currency;
import za.co.kemtech.agrigate.exceptions.CurrencyNotFoundException;
import za.co.kemtech.agrigate.repositories.CurrencyRepository;

import java.util.Optional;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency findByIsoCode(String isoCode){
        Optional<Currency> currencyOptional = currencyRepository.findByIsoCode(isoCode);
        if (currencyOptional.isPresent()){
            log.info("Currency " + isoCode + " Found");
            return currencyOptional.get();
        }
        log.info("Currency " + isoCode + " Not Found");
        throw new CurrencyNotFoundException("Currency " + isoCode + " Not Found");
    }

    public Currency add(Currency currency){
        return currencyRepository.save(currency);
    }
}
