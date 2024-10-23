package hiberspring.domain.dtos.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BranchSeedDTO implements Serializable {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
