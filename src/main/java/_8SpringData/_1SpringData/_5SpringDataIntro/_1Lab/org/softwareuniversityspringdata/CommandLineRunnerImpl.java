package _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata;

import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.Account;
import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.AccountService;
import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public CommandLineRunnerImpl(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
        User user = new User("Georgi", 31);
        this.userService.register(user);

        User userById = this.userService.findUserById(1L);
        Account account = new Account(BigDecimal.valueOf(5000), userById);
        this.accountService.createAccount(account);

        User georgi = this.userService.findByName("Georgi");
    }
}
