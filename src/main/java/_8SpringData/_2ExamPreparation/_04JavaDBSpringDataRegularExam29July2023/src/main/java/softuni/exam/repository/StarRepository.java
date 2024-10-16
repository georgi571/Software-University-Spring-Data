package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Optional<Star> findByName(String name);

    @Query("SELECT s FROM Star s LEFT JOIN s.astronomers a WHERE s.starType = :starType AND a IS NULL ORDER BY s.lightYears ASC")
    List<Star> findAllByStarTypeAndObservedIsNullOrderByLightYearsAsc(StarType starType);
}
