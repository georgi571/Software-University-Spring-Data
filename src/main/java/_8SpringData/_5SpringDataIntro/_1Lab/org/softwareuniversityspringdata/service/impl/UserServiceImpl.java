package _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.impl;

import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        User optionUser = this.userRepository.findByUsername(user.getUsername());
        if (optionUser == null) {
            this.userRepository.saveAndFlush(user);
        } else {
            System.out.printf("User with this username already exist.%n");
        }

    }

    @Override
    public User findUserById(Long id) {
        Optional<User> optionUser = this.userRepository.findById(id);
        if (optionUser.isPresent()) {
            return optionUser.get();
        }
        System.out.printf("No such user with given id - %d%n", id);
        return null;
    }

    @Override
    public User findByName(String name) {
        return this.userRepository.findByUsername(name);
    }


}
