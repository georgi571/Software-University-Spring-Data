package _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service;

import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.Account;

import java.math.BigDecimal;

public interface AccountService {
    void createAccount(Account account);
    void withdrawMoney(BigDecimal money, Long id);

    void transferMoney(BigDecimal money, Long id);
}
