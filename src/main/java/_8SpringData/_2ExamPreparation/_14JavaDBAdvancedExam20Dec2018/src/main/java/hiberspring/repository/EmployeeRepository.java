package hiberspring.repository;

import hiberspring.domain.entities.Employee;
import hiberspring.domain.entities.EmployeeCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCard(Optional<EmployeeCard> employeeCardOptional);

    @Query("SELECT e FROM Employee e WHERE e.branch.id IN " +
            "(SELECT b.id FROM Branch b WHERE SIZE(b.products) > 0) " +
            "ORDER BY CONCAT(e.firstName, ' ', e.lastName) ASC, LENGTH(e.position) DESC")
    List<Employee> findEmployeesWithBranchesHavingProducts();
}
