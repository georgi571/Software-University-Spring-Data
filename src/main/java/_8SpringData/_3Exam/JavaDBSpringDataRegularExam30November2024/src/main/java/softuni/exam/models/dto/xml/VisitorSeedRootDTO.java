package softuni.exam.models.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "visitors")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitorSeedRootDTO implements Serializable {

    @XmlElement(name = "visitor")
    private List<VisitorSeedDTO> visitors;

    public List<VisitorSeedDTO> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<VisitorSeedDTO> visitors) {
        this.visitors = visitors;
    }
}
