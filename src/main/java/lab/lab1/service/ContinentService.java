package lab.lab1.service;

import lab.lab1.entity.Continent;
import lab.lab1.repository.ContinentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContinentService {
    private ContinentRepository repository;

    @Autowired
    public ContinentService(ContinentRepository repository) {
        this.repository = repository;
    }

    public Optional<Continent> find(String name) {
        return repository.findById(name);
    }

    public List<Continent> findAll() {
        return repository.findAll();
    }
    @Transactional
    public void create(Continent continent) {
        repository.save(continent);
    }
    @Transactional
    public void delete(Continent continent) {
        repository.delete(continent);
    }
}
