package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Album;

import java.io.IOException;

public interface AlbumService {
    void seedAlbums() throws IOException;

    void createAlbum(Album album);

    Album getAlbumByName(String name);
}
