package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.imports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.adapters.LocalDateTimeAdapter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CustomerSeedDTO implements Serializable {

    @Expose
    private String name;

    @Expose
    @JsonAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @Expose
    private boolean isYoungDriver;

    public CustomerSeedDTO() {
    }

    public CustomerSeedDTO(String name, LocalDateTime birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
