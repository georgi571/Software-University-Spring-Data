package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
