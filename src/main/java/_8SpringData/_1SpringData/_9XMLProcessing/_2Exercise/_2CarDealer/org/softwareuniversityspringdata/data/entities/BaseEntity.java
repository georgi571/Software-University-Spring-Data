package _8SpringData._1SpringData._9XMLProcessing._2Exercise._2CarDealer.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }
}
