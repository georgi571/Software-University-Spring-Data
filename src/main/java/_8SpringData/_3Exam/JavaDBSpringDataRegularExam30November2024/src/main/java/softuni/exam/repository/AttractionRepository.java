package softuni.exam.repository;

import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Attraction;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    Optional<Attraction> findByName(@Size(min = 5, max = 40) String name);

    @Query("SELECT a FROM Attraction a WHERE (a.type = :firstType OR a.type = :secondType) AND a.elevation >= :elevation ORDER BY a.name ASC, a.country.name ASC")
    List<Attraction> findAllByTypeOrTypeAndElevationGreaterThanEqual(String firstType, String secondType, double elevation);
}
