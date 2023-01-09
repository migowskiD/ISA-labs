package lab.lab1.service;

import lab.lab1.entity.Country;
import lab.lab1.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private CountryRepository repository;

    @Autowired
    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    public Optional<Country> find(String name) {
        return repository.find(name);
    }

    public List<Country> findAll() {
        return repository.findAll();
    }
    public void create(Country country) {
        repository.create(country);
    }

    public void delete(String name) {
        repository.delete(repository.find(name).orElseThrow());
    }
}
