package lab.lab1.repository;


import lab.lab1.DataStore;
import lab.lab1.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CountryRepository implements Repository<Country, String>{

    private DataStore store;

    @Autowired
    public CountryRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Country> find(String name) {
        return store.findCountry(name);
    }

    @Override
    public List<Country> findAll() {
        return store.findAllCountries();
    }

    @Override
    public void create(Country entity) {
        store.createCountry(entity);
    }

    @Override
    public void delete(Country entity) {
        store.deleteCountry(entity.getName());
    }
}
