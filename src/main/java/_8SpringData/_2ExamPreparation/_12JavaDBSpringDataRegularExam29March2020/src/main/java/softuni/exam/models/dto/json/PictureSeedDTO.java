package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import softuni.exam.util.adapters.LocalDateTimeAdapterJSON;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class PictureSeedDTO implements Serializable {

    @Expose
    @Size(min = 2, max = 20)
    private String name;

    @Expose
    @JsonAdapter(LocalDateTimeAdapterJSON.class)
    private LocalDateTime dateAndTime;

    @Expose
    private Long car;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
