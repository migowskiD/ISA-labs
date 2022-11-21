package lab.lab1.service;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import lab.lab1.repository.ContinentRepository;
import lab.lab1.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository repository;
    private ContinentRepository continentRepository;

    @Autowired
    public CountryService(CountryRepository repository, ContinentRepository continentRepository) {
        this.repository = repository;
        this.continentRepository = continentRepository;
    }

    public Optional<Country> find(String name) {
        return repository.findById(name);
    }

    public Optional<Country> find(String name, String continentName) {
        Optional<Continent> continent = continentRepository.findById(continentName);
        if (continent.isPresent()) {
            return repository.findByNameAndContinent(name, continent.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Country> findAll() {
        return repository.findAll();
    }

    public List<Country> findAll(Continent continent) {
        return repository.findAllByContinent(continent);
    }
    @Transactional
    public Country create(Country country) {
        return repository.save(country);
    }
    @Transactional
    public void update(Country country) {
        repository.save(country);
    }
    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }
}
