package _8SpringData._1SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    protected BaseEntity() {

    }

    public BaseEntity(long id) {
        this.id = id;
    }
}
