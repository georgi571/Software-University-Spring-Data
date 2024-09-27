package _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.repositories;

import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountById(Long id);
}
