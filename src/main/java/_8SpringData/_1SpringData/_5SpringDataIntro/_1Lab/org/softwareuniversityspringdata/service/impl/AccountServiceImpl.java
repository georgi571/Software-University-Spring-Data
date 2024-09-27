package _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.Account;
import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.repositories.AccountRepository;
import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void createAccount(Account account) {
        this.accountRepository.saveAndFlush(account);
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        Optional<Account> optionalAccount = this.accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (account.getBalance().compareTo(money) >= 0) {
                account.setBalance(account.getBalance().subtract(money));
                this.accountRepository.saveAndFlush(account);
            } else {
                System.out.printf("Not enough money in account in your account.%n");
            }
        } else {
            System.out.printf("No such account with given id - %d.%n", id);
        }
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Optional<Account> optionalAccount = this.accountRepository.findById(id);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            if (money.compareTo(BigDecimal.ZERO) > 0) {
                account.setBalance(account.getBalance().subtract(money));
                this.accountRepository.saveAndFlush(account);
            }  else {
                System.out.printf("Cannot transfer negative amount of money.%n");
            }
        } else {
            System.out.printf("No such account with given id - %d.%n", id);
        }
    }
}
