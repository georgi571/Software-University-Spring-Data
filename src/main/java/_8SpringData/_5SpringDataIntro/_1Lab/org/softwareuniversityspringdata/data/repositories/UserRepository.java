package _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
