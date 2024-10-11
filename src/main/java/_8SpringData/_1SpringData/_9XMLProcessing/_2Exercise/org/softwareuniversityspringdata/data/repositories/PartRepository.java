package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.data.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

}
