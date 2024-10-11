package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Set<Car> findAllByMakeOrderByTravelledDistanceDesc(String model);

}
