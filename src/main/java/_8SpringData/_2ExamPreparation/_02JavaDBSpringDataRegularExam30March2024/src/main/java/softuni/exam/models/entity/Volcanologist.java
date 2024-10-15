package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "volcanologists")
public class Volcanologist extends BaseEntity{

    @Column(name = "first_name", nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "exploring_from")
    private LocalDate exploringFrom;

    @ManyToOne
    @JoinColumn(name = "exploring_volcano_id")
    private Volcano volcano;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getExploringFrom() {
        return exploringFrom;
    }

    public void setExploringFrom(LocalDate exploringFrom) {
        this.exploringFrom = exploringFrom;
    }

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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        salary = salary;
    }

    public Volcano getVolcano() {
        return volcano;
    }

    public void setVolcano(Volcano volcano) {
        this.volcano = volcano;
    }
}
