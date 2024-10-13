package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarMake;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarMakeRootDTO implements Serializable {

    @XmlElement(name = "car")
    List<CarMakeDTO> carMakeDTOList;

    public CarMakeRootDTO() {
    }

    public CarMakeRootDTO(List<CarMakeDTO> carMakeDTOList) {
        this.carMakeDTOList = carMakeDTOList;
    }

    public List<CarMakeDTO> getCarMakeDTOList() {
        return carMakeDTOList;
    }

    public void setCarMakeDTOList(List<CarMakeDTO> carMakeDTOList) {
        this.carMakeDTOList = carMakeDTOList;
    }
}
