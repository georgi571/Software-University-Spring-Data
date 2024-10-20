package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.json.UserSeedDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final String PATH_INPUT = "src/main/resources/files/users.json";

    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.userRepository.count() > 0;    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        UserSeedDTO[] userSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), UserSeedDTO[].class);
        for (UserSeedDTO userSeedDTO : userSeedDTOS) {
            Optional<User> sellerOptional = this.userRepository.findByUsername(userSeedDTO.getUsername());
            Optional<Picture> pictureOptional = this.pictureRepository.findByPath(userSeedDTO.getProfilePicture());
            if (!this.validationUtil.isValid(userSeedDTO) || sellerOptional.isPresent() || pictureOptional.isEmpty()) {
                stringBuilder.append("Invalid user").append(System.lineSeparator());
                continue;
            }
            User user = this.modelMapper.map(userSeedDTO, User.class);
            Picture picture = pictureOptional.get();
            user.setPicture(picture);
            this.userRepository.saveAndFlush(user);
            stringBuilder.append(String.format("Successfully imported User: %s", user.getUsername())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        return this.userRepository.findAllUsersWithPosts()
                .stream()
                .map(user -> {
                    String userHeader = String.format("User: %s\nPost count: %d\n", user.getUsername(), user.getPostSet().size());

                    String postsInfo = user.getPostSet().stream()
                            .sorted(Comparator.comparingDouble(post -> post.getPicture().getSize()))
                            .map(post -> String.format("==Post Details:\n" +
                                            "----Caption: %s\n" +
                                            "----Picture Size: %.2f\n",
                                    post.getCaption(),
                                    post.getPicture().getSize()))
                            .collect(Collectors.joining());

                    return userHeader + postsInfo;
                })
                .collect(Collectors.joining());
    }
}
