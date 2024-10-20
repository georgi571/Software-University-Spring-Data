package com.example.football.models.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersSeedRootDTO implements Serializable {

    @XmlElement(name = "player")
    private List<PlayersSeedDTO> playersSeedDTOList;

    public List<PlayersSeedDTO> getPlayersSeedDTOList() {
        return playersSeedDTOList;
    }

    public void setPlayersSeedDTOList(List<PlayersSeedDTO> playersSeedDTOList) {
        this.playersSeedDTOList = playersSeedDTOList;
    }
}
