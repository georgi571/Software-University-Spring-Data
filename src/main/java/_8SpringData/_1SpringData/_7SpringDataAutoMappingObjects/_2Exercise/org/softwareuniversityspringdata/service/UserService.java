package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.LoginUserDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.UserRegisterDTO;

public interface UserService {
    String registerUser(UserRegisterDTO registerUserDto);

    String loginUser(LoginUserDTO loginUserDTO);

    String logout();

    User getLoggedIn();
}
