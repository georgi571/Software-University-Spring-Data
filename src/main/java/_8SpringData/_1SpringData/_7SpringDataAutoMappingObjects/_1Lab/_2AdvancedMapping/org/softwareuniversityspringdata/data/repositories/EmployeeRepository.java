package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByFirstName(String employeeName);
}
