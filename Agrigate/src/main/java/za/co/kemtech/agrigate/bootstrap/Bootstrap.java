package za.co.kemtech.agrigate.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.kemtech.agrigate.entity.*;
import za.co.kemtech.agrigate.entity.enums.*;
import za.co.kemtech.agrigate.services.ClientService;
import za.co.kemtech.agrigate.services.FarmService;
import za.co.kemtech.agrigate.services.FarmerService;
import za.co.kemtech.agrigate.services.ServiceProviderService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import za.co.kemtech.agrigate.services.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class Bootstrap {

    @Autowired
    private ClientService clientService;

    @Autowired
    private FarmService farmService;

    @Autowired
    private FarmerService farmerService;

    @Autowired
    private ServiceProviderService serviceProviderService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private CurrencyService currencyService;

    public Bootstrap(ClientService clientService,
                     FarmService farmService,
                     FarmerService farmerService,
                     ServiceProviderService serviceProviderService,
                     CountryService countryService,
                     CurrencyService currencyService){
        super();
        this.clientService = clientService;
        this.farmService = farmService;
        this.farmerService = farmerService;
        this.serviceProviderService = serviceProviderService;
        loadClients();
        loadFarms();
        loadServiceProviders();
        this.countryService = countryService;
        this.currencyService = currencyService;
        loadClients();
        loadFarms();
        loadServiceProviders();
        loadCurrencies();
//        loadCountries();
    }

    private void loadClients(){

        List<Client> clients = new ArrayList<>();
        clients.add(new Client(
                UUID.randomUUID().toString(),
                ClientType.INDIVIDUAL,
                "Calvin Chirwa",
                "REG123456",
                "",
                new Address("7 Blesbok Rd","", "Randburg",2032,"Gauteng","South Africa"),
                new Contact("Calvin","Chirwa","0786101500","cschirwa@gmail.com"),
                "0110271585",
                "0786101500",
                "calvin@kemtech.co.za"
        ));

        clients.add(new Client(
                UUID.randomUUID().toString(),
                ClientType.INDIVIDUAL,
                "Sianni Chirwa",
                "REG123457",
                "",
                new Address("8","Blesbok Rd", "Randburg",2032,"Gauteng","South Africa"),
                new Contact("Calvin","Chirwa","0786101500","cschirwa@gmail.com"),
                "0110271586",
                "0786101501",
                "sinani@kemtech.co.za"
                ));

        clients.forEach(client -> clientService.add(client));

    }

    private void loadFarms(){
        List<Farm> farms = new ArrayList<>();

        Farmer farmer1 = loadFarmer();

        farms.add(new Farm(
                "Little Wells",
                "011011023",
                farmer1,
                Date.valueOf(LocalDate.of(2020,9,10)),
                "abs@def.com",
                FarmType.MEDIUM,
                new Address("Plot 123","","City",1212,"Mpumalanga", "South Africa"),
                new LatLong(121200.0012,234123.9000),
                "39Ha",
                "20Ha",
                "19Ha",
                ActivityType.PRODUCE
                ));

        farms.forEach(f -> farmService.add(f));
    }

    private Farmer loadFarmer(){
        Farmer farmer1 = new Farmer("Blessing","Chikoto",
                IdDocumentType.ID,
                "12312312312",
                FarmerType.INDIVIDUAL,
                "0898989898",
                "083454545",
                new Address("Plot 123","","City",1212,"Mpumalanga", "South Africa"),
                "blessing@gmail.com");
        return farmerService.add(farmer1);
    }

    private void loadServiceProviders(){
        ServiceProvider sp1 = new ServiceProvider(
                ServiceProviderType.EQUIPMENT,
                "John Deere",
                "2018/090909/11",
                "402030303000",
                new Address("Plot 1244","","Springs",1200,"Gauteng", "South Africa"),
                new Contact("Richard","Nkomo","122233441","richard@qwer.com"),
                "0119899999",
                "0789119110",
                "0781231233"
                );
        serviceProviderService.save(sp1);
    }

    private void loadCurrencies() {
        List<Currency> currencies = Arrays.asList(
                new Currency("ZAR", "Rand"),
                new Currency("BWP", "Pula"),
                new Currency("GBP", "Pound"),
                new Currency("USD", "Dollar")
        );
        currencies.forEach(ccy -> currencyService.add(ccy));
    }

    private void loadCountries(){
        List<Country> countryList = new ArrayList<>();
           countryList.add(new Country("MZ", "Mozambique", currencyService.findByIsoCode("USD")));
           countryList.add(new Country("BW", "Botswana", currencyService.findByIsoCode("BWP")));
           countryList.add(new Country("ZA", "South Africa", currencyService.findByIsoCode("ZAR")));
           countryList.add(new Country("NM", "Namibia", currencyService.findByIsoCode("ZAR")));
           countryList.add(new Country("ZW", "Zimbabwe", currencyService.findByIsoCode("USD")));
           countryList.add(new Country("ZM", "Zambia", currencyService.findByIsoCode("USD")));
        countryList.forEach(country -> countryService.add(country));
    }
}
