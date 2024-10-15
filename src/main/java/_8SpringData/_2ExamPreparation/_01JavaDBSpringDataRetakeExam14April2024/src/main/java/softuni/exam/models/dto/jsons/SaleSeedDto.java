package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import softuni.exam.util.adapters.LocalDateTimeAdapterJSON;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SaleSeedDto implements Serializable {

    @Expose
    private boolean discounted;

    @Expose
    @Size(min = 7, max = 7)
    private String number;

    @Expose
    @JsonAdapter(LocalDateTimeAdapterJSON.class)
    private LocalDateTime saleDate;

    @Expose
    private Long seller;

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }
}
