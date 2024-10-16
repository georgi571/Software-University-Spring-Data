package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import softuni.exam.models.entity.Astronomer;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.StarType;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

public class StarSeedDTO implements Serializable {
    @Expose
    @Size(min = 2, max = 30)
    private String name;

    @Expose
    @Positive
    private double lightYears;

    @Expose
    @Size(min = 6)
    private String description;

    @Expose
    private String starType;

    @Expose
    private Long constellation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStarType() {
        return starType;
    }

    public void setStarType(String starType) {
        this.starType = starType;
    }

    public Long getConstellation() {
        return constellation;
    }

    public void setConstellation(Long constellation) {
        this.constellation = constellation;
    }
}
