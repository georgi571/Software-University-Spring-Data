package softuni.exam.models.dto.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class CountrySeedDTO implements Serializable {

    @Expose
    @Size(min = 2, max = 30)
    private String name;

    @Expose
    @SerializedName("countryCode")
    @Size(min = 2, max = 19)
    private String code;

    @Expose
    @Size(min = 2, max = 19)
    private String currency;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
