package _8SpringData._1SpringData._9XMLProcessing._2Exercise.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsRootDTO implements Serializable {

    @XmlElement(name = "car")
    private List<CarAndPartsDTO> carAndPartsDTOList;

    public CarAndPartsRootDTO() {
    }

    public CarAndPartsRootDTO(List<CarAndPartsDTO> carAndPartsDTOList) {
        this.carAndPartsDTOList = carAndPartsDTOList;
    }

    public List<CarAndPartsDTO> getCarAndPartsDTOList() {
        return carAndPartsDTOList;
    }

    public void setCarAndPartsDTOList(List<CarAndPartsDTO> carAndPartsDTOList) {
        this.carAndPartsDTOList = carAndPartsDTOList;
    }
}
