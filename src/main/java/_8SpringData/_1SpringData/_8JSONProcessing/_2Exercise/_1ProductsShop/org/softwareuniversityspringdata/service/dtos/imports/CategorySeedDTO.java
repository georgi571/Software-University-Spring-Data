package _8SpringData._1SpringData._8JSONProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.service.dtos.imports;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CategorySeedDTO implements Serializable {
    @Expose
    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    public CategorySeedDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
