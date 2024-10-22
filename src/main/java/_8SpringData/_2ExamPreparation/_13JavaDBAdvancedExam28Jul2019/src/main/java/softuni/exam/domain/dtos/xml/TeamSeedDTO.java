package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedDTO implements Serializable {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "picture")
    private PictureSeedDTO picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureSeedDTO getPicture() {
        return picture;
    }

    public void setPicture(PictureSeedDTO picture) {
        this.picture = picture;
    }
}
