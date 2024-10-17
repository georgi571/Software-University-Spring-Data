package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;

import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    @Query("SELECT f FROM Forecast f " +
            "WHERE f.dayOfWeek = :dayOfWeek " +
            "AND f.city.population < :population " +
            "ORDER BY f.maxTemperature DESC, f.id ASC")
    List<Forecast> findSundayForecastsInSmallCities(DayOfWeek dayOfWeek, int population);
}
