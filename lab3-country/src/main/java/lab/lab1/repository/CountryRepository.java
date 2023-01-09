package lab.lab1.repository;

import lab.lab1.entity.Continent;
import lab.lab1.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findAllByContinent(Continent continent);
    Optional<Country> findByNameAndContinent(String id, Continent continent);
}
