package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.User;

import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException;

    void addFriends(User user, User user1);

    User getUserById(Long id);

    void createUser(User User);
}
