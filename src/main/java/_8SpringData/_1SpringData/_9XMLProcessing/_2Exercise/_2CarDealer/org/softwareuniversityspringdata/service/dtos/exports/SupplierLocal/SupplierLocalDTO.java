package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.service.dtos.exports.SupplierLocal;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierLocalDTO implements Serializable {


    @XmlAttribute(name = "id")
    private long id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "parts-count")
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
