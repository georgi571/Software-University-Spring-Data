package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.xml.PostSeedDTO;
import softuni.exam.instagraphlite.models.dto.xml.PostSeedRootDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.models.entity.Post;
import softuni.exam.instagraphlite.models.entity.User;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    private final String PATH_INPUT = "src/main/resources/files/posts.xml";

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.postRepository.count() > 0;    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));
    }

    @Override
    public String importPosts() throws  JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        PostSeedRootDTO postSeedRootDTO = this.xmlParser.parse(PostSeedRootDTO.class, PATH_INPUT);
        for (PostSeedDTO postSeedDTO : postSeedRootDTO.getPostSeedDTOList()) {
            Optional<User> userOptional = this.userRepository.findByUsername(postSeedDTO.getUserPostDTO().getUsername());
            Optional<Picture> pictureOptional = this.pictureRepository.findByPath(postSeedDTO.getPicturePostDTO().getPath());
            if (!this.validationUtil.isValid(postSeedDTO) || userOptional.isEmpty() || pictureOptional.isEmpty()) {
                stringBuilder.append("Invalid post").append(System.lineSeparator());
                continue;
            }
            Post post = this.modelMapper.map(postSeedDTO, Post.class);
            Picture picture = pictureOptional.get();
            User user = userOptional.get();
            post.setUser(user);
            post.setPicture(picture);
            this.postRepository.saveAndFlush(post);
            stringBuilder.append(String.format("Successfully imported Post, made by %s", post.getUser().getUsername())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }
}
