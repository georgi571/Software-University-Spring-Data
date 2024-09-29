package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Town;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.TownRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.UserService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private static final String FILE_PATH = "src/main/resources/files/users.txt";
    private final UserRepository userRepository;
    private final TownRepository townRepository;

    public UserServiceImpl(UserRepository userRepository, TownRepository townRepository) {
        this.userRepository = userRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] parts = row.split(", ");
                        String username = parts[0];
                        String password = parts[1];
                        String email = parts[2];
                        LocalDateTime registrationDate = LocalDateTime.now();
                        LocalDateTime lastLoginDate = LocalDateTime.now();
                        int age = Integer.parseInt(parts[3]);
                        String bornTownName = parts[4];
                        String currentLivingTownName = parts[5];
                        String firstName = parts[6];
                        String lastName = parts[7];
                        Town bornTown = this.townRepository.findByName(bornTownName);
                        Town currentLivingTown = this.townRepository.findByName(currentLivingTownName);
                        User user = new User(username, password, email, registrationDate, lastLoginDate, age, bornTown, currentLivingTown, firstName, lastName);
                        this.userRepository.saveAndFlush(user);
                    });
        }
    }

    @Override
    public void addFriends(User user, User user1) {
        user.getFriends().add(user1);
        user1.getFriends().add(user);
        this.userRepository.saveAndFlush(user);
        this.userRepository.saveAndFlush(user1);
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findAllById(id);
    }

    @Override
    public void createUser(User user) {
        this.userRepository.saveAndFlush(user);
    }
}
