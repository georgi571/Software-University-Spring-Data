package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.service;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Picture;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.Album;

import java.io.IOException;

public interface PictureService {
    void seedPictures() throws IOException;

    void createPicture(Picture picture, Album album);
}