package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.domain.dtos.xml.PictureSeedDTO;
import softuni.exam.domain.dtos.xml.PictureSeedRootDTO;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final String PATH_INPUT = "src/main/resources/files/xml/pictures.xml";

    private final PictureRepository pictureRepository;

    private final Gson gson;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidatorUtil validationUtil;
    private final FileUtil fileUtil;

    public PictureServiceImpl(PictureRepository pictureRepository, Gson gson, XmlParser xmlParser, ModelMapper modelMapper, ValidatorUtil validationUtil, FileUtil fileUtil) {
        this.pictureRepository = pictureRepository;
        this.gson = gson;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public String importPictures() throws IOException {
        return this.fileUtil.readFile(PATH_INPUT);
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        PictureSeedRootDTO pictureSeedRootDTO = this.xmlParser.parse(PictureSeedRootDTO.class, PATH_INPUT);
        for (PictureSeedDTO pictureSeedDTO : pictureSeedRootDTO.getPictureSeedDTOS()) {
            Optional<Picture> pictureOptional = this.pictureRepository.findByUrl(pictureSeedDTO.getUrl());
            if (!this.validationUtil.isValid(pictureSeedDTO) || pictureOptional.isPresent()) {
                stringBuilder.append("Invalid picture").append(System.lineSeparator());
                continue;
            }
            Picture picture = this.modelMapper.map(pictureSeedDTO, Picture.class);
            this.pictureRepository.saveAndFlush(picture);
            stringBuilder.append(String.format("Successfully imported picture - %s", picture.getUrl())).append(System.lineSeparator());
        }

        return stringBuilder.toString().trim();
    }

}
