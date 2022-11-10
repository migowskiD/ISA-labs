package lab.lab1.repository;

import lab.lab1.entity.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface ContinentRepository extends JpaRepository<Continent, String> {

}
