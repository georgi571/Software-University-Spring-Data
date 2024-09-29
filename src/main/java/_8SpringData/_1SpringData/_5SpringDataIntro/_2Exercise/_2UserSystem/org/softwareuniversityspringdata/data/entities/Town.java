package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(mappedBy = "bornTown")
    private Set<User> usersBornInTown;

    @OneToMany(mappedBy = "currentlyLivingTown")
    private Set<User> usersLivingInTown;

    public Town() {
        usersBornInTown = new HashSet<>();
        usersLivingInTown = new HashSet<>();
    }

    public Town(String name, Country country) {
        this.name = name;
        this.country = country;
        usersBornInTown = new HashSet<>();
        usersLivingInTown = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
