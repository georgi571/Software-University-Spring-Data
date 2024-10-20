package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.models.dto.json.PictureSeedDTO;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.util.ValidationUtil;
import softuni.exam.instagraphlite.util.XmlParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PictureServiceImpl implements PictureService {

    private final String PATH_INPUT = "src/main/resources/files/pictures.json";

    private final PictureRepository pictureRepository;
    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;    }

    @Override
    public String readFromFileContent() throws IOException {
        return new String(Files.readAllBytes(Path.of(PATH_INPUT)));    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        PictureSeedDTO[] pictureSeedDTOS = this.gson.fromJson(new FileReader(PATH_INPUT), PictureSeedDTO[].class);
        for (PictureSeedDTO pictureSeedDTO : pictureSeedDTOS) {
            Optional<Picture> pictureOptional = this.pictureRepository.findByPath(pictureSeedDTO.getPath());
            if (!this.validationUtil.isValid(pictureSeedDTO) || pictureOptional.isPresent()) {
                stringBuilder.append("Invalid picture").append(System.lineSeparator());
                continue;
            }
            Picture picture = this.modelMapper.map(pictureSeedDTO, Picture.class);
            this.pictureRepository.saveAndFlush(picture);
            stringBuilder.append(String.format("Successfully imported Picture, with size %.2f", picture.getSize())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportPictures() {
        return this.pictureRepository.findAllBySizeGreaterThanOrderBySizeAsc(30000)
                .stream()
                .map(picture -> String.format("%.2f – %s\n",
                        picture.getSize(),
                        picture.getPath()))
                .collect(Collectors.joining());
    }
}
