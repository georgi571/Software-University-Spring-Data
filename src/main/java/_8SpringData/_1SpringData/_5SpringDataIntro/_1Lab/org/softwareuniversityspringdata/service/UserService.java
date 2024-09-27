package _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities.User;

public interface UserService {
    void register(User user);

    User findUserById(Long id);

    User findByName(String name);
}
