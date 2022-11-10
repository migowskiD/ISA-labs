package lab.lab1.service;

import lab.lab1.entity.Country;
import lab.lab1.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return repository.findById(name);
    }

    public List<Country> findAll() {
        return repository.findAll();
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
