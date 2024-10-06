package _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.LoginUserDTO;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.dtos.UserRegisterDTO;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.service.UserService;
import _8SpringData._1SpringData._7SpringDataAutoMappingObjects._2Exercise.org.softwareuniversityspringdata.util.ValidatorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private User logIn;

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, ValidatorService validatorService) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.validatorService = validatorService;
    }

    @Override
    public String registerUser(UserRegisterDTO registerUserDTO) {
        if (!this.validatorService.isValid(registerUserDTO)) {
            Set<ConstraintViolation<UserRegisterDTO>> set = this.validatorService.validate(registerUserDTO);
            return set.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining("\n"));
        }

        if (!registerUserDTO.getPassword().equals(registerUserDTO.getConfirmPassword())) {
            return "Password don't match";
        }

        Optional<User> optional = this.userRepository.findUserByEmail(registerUserDTO.getEmail());
        if (optional.isPresent()) {
            return "User with that email already exist.";
        }

        User user = this.modelMapper.map(registerUserDTO, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdmin(true);
        }

        this.userRepository.saveAndFlush(user);


        return String.format("%s was registered.", user.getFullName());
    }

    @Override
    public String loginUser(LoginUserDTO loginUserDTO) {
        Optional<User> optional = this.userRepository.findByEmailAndPassword(loginUserDTO.getEmail(), loginUserDTO.getPassword());

        if (optional.isEmpty()) {
            return "Incorrect email/password";
        }

        setLogIn(optional.get());

        return String.format("Successfully logged in %s", optional.get().getFullName());
    }

    @Override
    public String logout() {
        if (this.logIn == null) {
            return "Cannot log out. No user was logged in";
        }

        String userFullName = this.logIn.getFullName();

        setLogIn(null);

        return String.format("User %s successfully log out", userFullName);
    }

    @Override
    public User getLoggedIn() {
        return this.logIn;
    }

    public void setLogIn(User logIn) {
        this.logIn = logIn;
    }
}
