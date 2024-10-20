package softuni.exam.instagraphlite.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedRootDTO implements Serializable {

    @XmlElement(name = "post")
    private List<PostSeedDTO> postSeedDTOList;

    public List<PostSeedDTO> getPostSeedDTOList() {
        return postSeedDTOList;
    }

    public void setPostSeedDTOList(List<PostSeedDTO> postSeedDTOList) {
        this.postSeedDTOList = postSeedDTOList;
    }
}
