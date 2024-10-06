package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._1Lab._2AdvancedMapping.org.softwareuniversityspringdata.data.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
