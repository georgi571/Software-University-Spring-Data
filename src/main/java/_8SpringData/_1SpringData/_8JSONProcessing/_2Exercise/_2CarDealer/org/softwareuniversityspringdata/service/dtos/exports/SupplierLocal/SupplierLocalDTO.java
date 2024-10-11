package _8SpringData._1SpringData._8JSONProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SupplierLocalDTO implements Serializable {

    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private int partsCount;

    public SupplierLocalDTO() {
    }

    public SupplierLocalDTO(long id, String name, int partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
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

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
