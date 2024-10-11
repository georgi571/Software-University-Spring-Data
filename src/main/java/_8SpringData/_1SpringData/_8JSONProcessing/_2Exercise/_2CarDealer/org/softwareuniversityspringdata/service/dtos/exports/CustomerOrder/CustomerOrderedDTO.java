package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.CustomerOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.util.adapters.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomerOrderedDTO {

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    @JsonAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @Expose
    private boolean isYoungDriver;

    @Expose
    private Set<SalesOrderedDTO> sale;

    public CustomerOrderedDTO() {
    }

    public CustomerOrderedDTO(long id, String name, LocalDateTime birthDate, boolean isYoungDriver, Set<SalesOrderedDTO> sale) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sale = sale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<SalesOrderedDTO> getSale() {
        return sale;
    }

    public void setSale(Set<SalesOrderedDTO> sale) {
        this.sale = sale;
    }
}
