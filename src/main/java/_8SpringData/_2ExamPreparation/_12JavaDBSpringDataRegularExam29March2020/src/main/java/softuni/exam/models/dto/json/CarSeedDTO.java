package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import softuni.exam.util.adapters.LocalDateAdapterJSON;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class CarSeedDTO implements Serializable {

    @Expose
    @Size(min = 2, max = 20)
    private String make;

    @Expose
    @Size(min = 2, max = 20)
    private String model;

    @Expose
    @Positive
    private int kilometers;

    @Expose
    @JsonAdapter(LocalDateAdapterJSON.class)
    private LocalDate registeredOn;

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

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }
}
