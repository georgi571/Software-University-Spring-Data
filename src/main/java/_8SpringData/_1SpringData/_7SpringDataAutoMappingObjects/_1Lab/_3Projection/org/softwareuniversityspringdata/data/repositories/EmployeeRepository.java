package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.data.entities.Employee;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._3Projection.org.softwareuniversityspringdata.service.dtos.EmployeeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String employeeName);
    @Query("SELECT new org.softwareuniversityspringdata.service.dtos.EmployeeDTO(e.firstName, e.lastName, e.salary, e.manager) " +
            "FROM Employee e LEFT JOIN e.manager WHERE e.birthday < :before ORDER BY e.salary DESC")
    List<EmployeeDTO> findAllByBirthdayBefore(LocalDate before);
}
