package softuni.exam.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "population", nullable = false)
    private int population;

    @Column(name = "guide", nullable = false)
    private String guide;

    @OneToMany(mappedBy = "town")
    private Set<Passenger> ticketSet;

    @OneToMany(mappedBy = "fromTown")
    private Set<Ticket> from;

    @OneToMany(mappedBy = "toTown")
    private Set<Ticket> to;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public Set<Passenger> getTicketSet() {
        return ticketSet;
    }

    public void setTicketSet(Set<Passenger> ticketSet) {
        this.ticketSet = ticketSet;
    }

    public Set<Ticket> getFrom() {
        return from;
    }

    public void setFrom(Set<Ticket> from) {
        this.from = from;
    }

    public Set<Ticket> getTo() {
        return to;
    }

    public void setTo(Set<Ticket> to) {
        this.to = to;
    }
}
