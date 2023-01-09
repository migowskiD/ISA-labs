package lab.lab1;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lab.lab1.service.ContinentService;
import lab.lab1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private CountryService countryService;
    private ContinentService continentService;

    @Autowired
    public CommandLine(CountryService countryService, ContinentService continentService) {
        this.countryService = countryService;
        this.continentService = continentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Type '0' to list all commands");
        while(true) {
            System.out.println("Type a command:");
            String s = in.nextLine();
            switch (s) {
                case "0":
                    System.out.println("List of commands:");
                    System.out.println("0 - listing all commands");
                    System.out.println("1 - listing all continents");
                    System.out.println("2 - listing all countries");
                    System.out.println("3 - adding new country");
                    System.out.println("4 - delete existing country");
                    System.out.println("5 - stop the application");
                    break;
                case "1":
                    continentService.findAll().forEach(System.out::println);
                    break;
                case "2":
                    countryService.findAll().forEach(System.out::println);
                    break;
                case "3":
                    System.out.println("Type country's name:");
                    String name = in.nextLine();
                    System.out.println("Type country's capital city name:");
                    String capitalCity = in.nextLine();
                    System.out.println("Type country's GDP per capita:");
                    int perCapitaGDP = Integer.parseInt(in.nextLine());
                    System.out.println("Choose country's continent name:");
                    continentService.findAll().forEach(System.out::println);
                    Continent continent = continentService.find(in.nextLine()).orElseThrow();
                    Country country = Country.builder()
                        .name(name)
                        .capitalCity(capitalCity)
                        .perCapitaGDP(perCapitaGDP)
                        .continent(continent)
                        .build();
                    countryService.create(country);
                    break;
                case "4":
                    System.out.println("Type country's name:");
                    String nameToDelete = in.nextLine();
                    countryService.delete(nameToDelete);
                    break;
                case "5":
                    System.out.println("Stopping application");
                    return;
            }
        }
    }
}
