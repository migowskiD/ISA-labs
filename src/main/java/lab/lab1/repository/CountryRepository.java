package lab.lab1.repository;

import lab.lab1.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
