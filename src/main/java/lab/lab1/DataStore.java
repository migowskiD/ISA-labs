package lab.lab1;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {

    /**
     * Set of all continents.
     */
    private Set<Continent> continents = new HashSet<>();

    /**
     * Set of all countries.
     */
    private Set<Country> countries = new HashSet<>();

    public synchronized List<Continent> findAllContinents() {
        return new ArrayList<>(continents);
    }

    public synchronized List<Country> findAllCountries() {
        return new ArrayList<>(countries);
    }

    public Optional<Continent> findContinent(String name) {
        return continents.stream()
                .filter(Continent -> Continent.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createContinent(Continent Continent) throws IllegalArgumentException {
        findContinent(Continent.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The Continent name \"%s\" is not unique", Continent.getName()));
                },
                () -> continents.add(Continent));
    }

    public synchronized void deleteContinent(String name) throws IllegalArgumentException {
        findContinent(name).ifPresentOrElse(
                original -> continents.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The Continent with name \"%d\" does not exist", name));
                });
    }

    public Optional<Country> findCountry(String name) {
        return countries.stream()
                .filter(Country -> Country.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCountry(Country Country) throws IllegalArgumentException {
        findCountry(Country.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The Country name \"%s\" is not unique", Country.getName()));
                },
                () -> countries.add(Country));
    }

    public synchronized void deleteCountry(String name) throws IllegalArgumentException {
        findCountry(name).ifPresentOrElse(
                original -> countries.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The Country with name \"%d\" does not exist", name));
                });
    }
    
}
