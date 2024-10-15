package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Volcano;

import java.util.List;
import java.util.Optional;

@Repository
public interface VolcanoRepository extends JpaRepository<Volcano, Long> {

    Optional<Volcano> findByName(String name);

    @Query("SELECT v FROM Volcano v WHERE v.elevation > :elevation AND v.lastEruption IS NOT NULL AND v.isActive = true ORDER BY v.elevation DESC")
    List<Volcano> findAllByIsActiveAndElevationGreaterThanAndLastEruptionIsNotNullOrderByElevationDesc(int elevation);
}
