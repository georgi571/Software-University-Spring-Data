package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.impl;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.UserRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Album;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.User;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.repositories.AlbumRepository;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service.AlbumService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AlbumServiceImpl implements AlbumService {
    private static final String FILE_PATH = "src/main/resources/files/albums.txt";
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void seedAlbums() throws IOException {
        if (this.albumRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH))
                    .stream()
                    .filter(row -> !row.isEmpty())
                    .forEach(row -> {
                        String[] parts = row.split(", ");
                        String albumName = parts[0];
                        String backgroundColor = parts[1];
                        boolean isPublicAlbum = Boolean.parseBoolean(parts[2]);
                        String username = parts[3];
                        User user = this.userRepository.findByUsername(username);
                        Album album = new Album(albumName, backgroundColor, isPublicAlbum, user);
                        this.albumRepository.saveAndFlush(album);
                    });
        }

    }

    @Override
    public void createAlbum(Album album) {
        this.albumRepository.saveAndFlush(album);
    }

    @Override
    public Album getAlbumByName(String name) {
        return this.albumRepository.findByName(name);
    }
}
