package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import softuni.exam.util.adapters.LocalDateTimeAdapterJSON;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

public class VolcanoSeedDTO implements Serializable {

    @Expose
    @Size(min = 2, max = 30)
    private String name;

    @Expose
    @Positive
    private int elevation;

    @Expose
    private String volcanoType;

    @Expose
    private boolean isActive;

    @Expose
    @JsonAdapter(LocalDateTimeAdapterJSON.class)
    private LocalDate lastEruption;

    @Expose
    private Long country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getVolcanoType() {
        return volcanoType;
    }

    public void setVolcanoType(String volcanoType) {
        this.volcanoType = volcanoType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getLastEruption() {
        return lastEruption;
    }

    public void setLastEruption(LocalDate lastEruption) {
        this.lastEruption = lastEruption;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
