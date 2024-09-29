package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Album;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Picture;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.AlbumRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.PictureRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.PictureService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PictureServiceImpl implements PictureService {
    private static final String FILE_PATH = "src/main/resources/files/pictures.txt";
    private final PictureRepository pictureRepository;
    private final AlbumRepository albumRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, AlbumRepository albumRepository) {
        this.pictureRepository = pictureRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void seedPictures() throws IOException {
        if (this.pictureRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] parts = row.split(", ");
                        String title = parts[0];
                        String caption = parts[1];
                        String path = parts[2];
                        String albumName = parts[3];
                        Album album = this.albumRepository.findByName(albumName);
                        Picture picture = this.pictureRepository.findByTitle(title);
                        if (picture == null) {
                            picture = new Picture(title, caption, path);
                        }
                        picture.addAlbum(album);
                        album.addPicture(picture);
                        this.pictureRepository.saveAndFlush(picture);
                        this.albumRepository.saveAndFlush(album);
                    });
        }
    }

    @Override
    public void createPicture(Picture picture, Album album) {
        picture.addAlbum(album);
        album.addPicture(picture);
        this.pictureRepository.saveAndFlush(picture);
        this.albumRepository.saveAndFlush(album);
    }
}
