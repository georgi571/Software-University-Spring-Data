package com.example.football.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "stats")
@XmlAccessorType(XmlAccessType.FIELD)
public class StatsSeedRootDTO implements Serializable {

    @XmlElement(name = "stat")
    private List<StatSeedDTO> statSeedDTOS;

    public List<StatSeedDTO> getStatSeedDTOS() {
        return statSeedDTOS;
    }

    public void setStatSeedDTOS(List<StatSeedDTO> statSeedDTOS) {
        this.statSeedDTOS = statSeedDTOS;
    }
}
