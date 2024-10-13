package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts;

import jakarta.xml.bind.annotation.*;

import java.io.Serializable;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarAndPartsDTO implements Serializable {

    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "model")
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private long travelledDistance;

    @XmlElement(name = "parts")
    private PartRootDTO partRootDTO;

    public CarAndPartsDTO() {
    }

    public CarAndPartsDTO(String make, String model, long travelledDistance, PartRootDTO partRootDTO) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.partRootDTO = partRootDTO;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public PartRootDTO getPartRootDTO() {
        return partRootDTO;
    }

    public void setPartRootDTO(PartRootDTO partRootDTO) {
        this.partRootDTO = partRootDTO;
    }
}
