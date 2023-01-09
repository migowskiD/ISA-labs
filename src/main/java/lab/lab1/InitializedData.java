package lab.lab1;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lab.lab1.service.ContinentService;
import lab.lab1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final CountryService countryService;

    private final ContinentService continentService;

    @Autowired
    public InitializedData(CountryService countryService, ContinentService continentService) {
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @PostConstruct
    private synchronized void init() {
        Continent europa = Continent.builder()
                .name("Europa")
                .area(10180000)
                .populationDensity(72.9)
                .build();

        Continent azja = Continent.builder()
                .name("Azja")
                .area(44579000)
                .populationDensity(100.0)
                .build();

        Continent amerykaPolnocna = Continent.builder()
                .name("Ameryka Polnocna")
                .area(24709000)
                .populationDensity(25.7)
                .build();

        continentService.create(europa);
        continentService.create(azja);
        continentService.create(amerykaPolnocna);

        Country polska = Country.builder()
                .name("Polska")
                .capitalCity("Warszawa")
                .perCapitaGDP(19023)
                .continent(europa)
                .build();

        Country niemcy = Country.builder()
                .name("Niemcy")
                .capitalCity("Berlin")
                .perCapitaGDP(48398)
                .continent(europa)
                .build();

        Country stanyZjednoczone = Country.builder()
                .name("Stany Zjednoczone")
                .capitalCity("Waszyngton")
                .perCapitaGDP(75179)
                .continent(amerykaPolnocna)
                .build();

        Country norwegia = Country.builder()
                .name("Norwegia")
                .capitalCity("Oslo")
                .perCapitaGDP(92646)
                .continent(europa)
                .build();

        countryService.create(polska);
        countryService.create(niemcy);
        countryService.create(stanyZjednoczone);
        countryService.create(norwegia);
    }
}
