package lab.lab1;

import lab.lab1.entity.Continent;
import lab.lab1.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final ContinentService continentService;

    @Autowired
    public InitializedData(ContinentService continentService) {
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
    }
}
