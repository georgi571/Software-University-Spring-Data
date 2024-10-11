package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Set<Customer> findAllByOrderByBirthDateAscIsYoungDriverAsc();

    @Query(value = "FROM Customer WHERE size(sales) > 0")
    Set<Customer> findAllByWithBoughtCars();
}
