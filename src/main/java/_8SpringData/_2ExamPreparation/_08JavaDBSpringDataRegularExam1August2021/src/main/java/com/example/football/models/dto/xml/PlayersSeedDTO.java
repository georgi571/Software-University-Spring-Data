package com.example.football.models.dto.xml;

import com.example.football.util.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement(name = "player")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayersSeedDTO implements Serializable {

    @XmlElement(name = "first-name")
    @Size(min = 2)
    private String firstName;

    @XmlElement(name = "last-name")
    @Size(min = 2)
    private String lastName;

    @XmlElement(name = "email")
    @Email
    private String email;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    @NotNull
    private LocalDate birthDate;

    @XmlElement(name = "position")
    private String position;

    @XmlElement(name = "stat")
    private StatPlayerSeedDTO stat;

    @XmlElement(name = "team")
    private TeamPlayerSeedDTO team;

    @XmlElement(name = "town")
    private TownPlayerSeedDTO town;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public StatPlayerSeedDTO getStat() {
        return stat;
    }

    public void setStat(StatPlayerSeedDTO stat) {
        this.stat = stat;
    }

    public TeamPlayerSeedDTO getTeam() {
        return team;
    }

    public void setTeam(TeamPlayerSeedDTO team) {
        this.team = team;
    }

    public TownPlayerSeedDTO getTown() {
        return town;
    }

    public void setTown(TownPlayerSeedDTO town) {
        this.town = town;
    }
}
