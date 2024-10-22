package softuni.exam.domain.dtos.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class TeamDTO implements Serializable {

    @Expose
    private String name;

    @Expose
    private PictureDTO picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureDTO picture) {
        this.picture = picture;
    }
}
