package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CarAndParts;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class CarAndPartsDTO implements Serializable {

    @Expose
    private CarDTO car;

    @Expose
    private List<PartDTO> parts;

    public CarAndPartsDTO() {
    }

    public CarAndPartsDTO(CarDTO car, List<PartDTO> parts) {
        this.car = car;
        this.parts = parts;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public List<PartDTO> getParts() {
        return parts;
    }

    public void setParts(List<PartDTO> parts) {
        this.parts = parts;
    }
}
