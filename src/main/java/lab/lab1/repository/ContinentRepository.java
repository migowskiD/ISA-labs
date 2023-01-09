package lab.lab1.repository;

import lab.lab1.DataStore;
import lab.lab1.entity.Continent;
import lab.lab1.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class ContinentRepository implements Repository<Continent, String>{

    private DataStore store;

    @Autowired
    public ContinentRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Continent> find(String name) {
        return store.findContinent(name);
    }

    @Override
    public List<Continent> findAll() {
        return store.findAllContinents();
    }

    @Override
    public void create(Continent entity) {
        store.createContinent(entity);
    }

    @Override
    public void delete(Continent entity) {
        store.deleteContinent(entity.getName());
    }
}
