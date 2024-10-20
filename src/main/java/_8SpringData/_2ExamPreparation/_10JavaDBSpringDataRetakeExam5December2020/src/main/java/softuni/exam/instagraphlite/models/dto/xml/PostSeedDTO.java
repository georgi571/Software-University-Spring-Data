package softuni.exam.instagraphlite.models.dto.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDTO implements Serializable {

    @XmlElement(name = "caption")
    @NotNull
    @Size(min = 21)
    private String caption;

    @XmlElement(name = "user")
    @NotNull
    private UserPostDTO userPostDTO;

    @XmlElement(name = "picture")
    @NotNull
    private PicturePostDTO picturePostDTO;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public UserPostDTO getUserPostDTO() {
        return userPostDTO;
    }

    public void setUserPostDTO(UserPostDTO userPostDTO) {
        this.userPostDTO = userPostDTO;
    }

    public PicturePostDTO getPicturePostDTO() {
        return picturePostDTO;
    }

    public void setPicturePostDTO(PicturePostDTO picturePostDTO) {
        this.picturePostDTO = picturePostDTO;
    }
}
