package hiberspring.domain.dtos.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeSeedRootDTO implements Serializable {

    @XmlElement(name = "employee")
    private List<EmployeeSeedDTO> employeeSeedDTOS;

    public List<EmployeeSeedDTO> getEmployeeSeedDTOS() {
        return employeeSeedDTOS;
    }

    public void setEmployeeSeedDTOS(List<EmployeeSeedDTO> employeeSeedDTOS) {
        this.employeeSeedDTOS = employeeSeedDTOS;
    }
}
