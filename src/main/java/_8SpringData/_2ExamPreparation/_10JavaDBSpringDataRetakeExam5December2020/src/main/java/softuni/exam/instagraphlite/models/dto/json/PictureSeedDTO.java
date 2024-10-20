package softuni.exam.instagraphlite.models.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PictureSeedDTO implements Serializable {

    @Expose
    @NotNull
    private String path;

    @Expose
    @DecimalMin(value = "500")
    @DecimalMax(value = "60000")
    private double size;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
