package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {
    Town findByName(String name);
}
