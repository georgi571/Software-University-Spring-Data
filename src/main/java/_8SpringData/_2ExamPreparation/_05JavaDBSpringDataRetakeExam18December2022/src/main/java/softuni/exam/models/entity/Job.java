package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "hoursaweek", nullable = false)
    private double hoursAWeek;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    private String description;

    @ManyToMany(mappedBy = "jobs")
    private Set<Company> companies = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getHoursAWeek() {
        return hoursAWeek;
    }

    public void setHoursAWeek(double hoursAWeek) {
        this.hoursAWeek = hoursAWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
}
