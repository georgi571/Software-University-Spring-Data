package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "town_name", nullable = false, unique = true)
    private String townName;

    @Column(name = "population", nullable = false)
    private int population;

    @OneToMany(mappedBy = "town")
    private Set<Agent> agentSet;

    @OneToMany(mappedBy = "town")
    private Set<Apartment> apartmentSet;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Set<Agent> getAgentSet() {
        return agentSet;
    }

    public void setAgentSet(Set<Agent> agentSet) {
        this.agentSet = agentSet;
    }

    public Set<Apartment> getApartmentSet() {
        return apartmentSet;
    }

    public void setApartmentSet(Set<Apartment> apartmentSet) {
        this.apartmentSet = apartmentSet;
    }
}
