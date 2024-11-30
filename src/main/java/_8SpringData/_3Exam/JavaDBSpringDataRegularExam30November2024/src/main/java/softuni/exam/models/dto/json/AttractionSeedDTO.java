package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class AttractionSeedDTO implements Serializable {

    @Expose
    @Size(min = 5, max = 40)
    private String name;

    @Expose
    @Size(min = 10, max = 100)
    private String description;

    @Expose
    @Size(min = 3, max = 30)
    private String type;

    @Expose
    @PositiveOrZero
    private int elevation;

    @Expose
    private Long country;

    public @Size(min = 5, max = 40) String getName() {
        return name;
    }

    public void setName(@Size(min = 5, max = 40) String name) {
        this.name = name;
    }

    public @Size(min = 10, max = 100) String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 10, max = 100) String description) {
        this.description = description;
    }

    public @Size(min = 3, max = 30) String getType() {
        return type;
    }

    public void setType(@Size(min = 3, max = 30) String type) {
        this.type = type;
    }

    @PositiveOrZero
    public int getElevation() {
        return elevation;
    }

    public void setElevation(@PositiveOrZero int elevation) {
        this.elevation = elevation;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
