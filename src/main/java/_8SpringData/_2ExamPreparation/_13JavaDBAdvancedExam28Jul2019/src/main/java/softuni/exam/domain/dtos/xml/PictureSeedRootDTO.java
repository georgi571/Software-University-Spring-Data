package softuni.exam.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class PictureSeedRootDTO implements Serializable {

    @XmlElement(name = "picture")
    private List<PictureSeedDTO> pictureSeedDTOS;

    public List<PictureSeedDTO> getPictureSeedDTOS() {
        return pictureSeedDTOS;
    }

    public void setPictureSeedDTOS(List<PictureSeedDTO> pictureSeedDTOS) {
        this.pictureSeedDTOS = pictureSeedDTOS;
    }
}
