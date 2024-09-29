package _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities;

import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.anotation.Email;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import _8SpringData._1SpringData._5SpringDataIntro._2Exercise._2UserSystem.org.softwareuniversityspringdata.data.entities.anotation.Password;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username", nullable = false)
    @Length(min = 4, max = 30)
    private String username;

    @Column(name = "password", nullable = false)
    @Password()
    private String password;

    @Column(name = "email", nullable = false)
    @Email()
    private String email;

    @Column(name = "registered_on", nullable = false)
    private LocalDateTime registrationDateAndTime;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastLoginDateAndTime;

    @Column(name = "age")
    @Min(1)
    @Max(120)
    private int age;

    @Column(name = "is_deleted", nullable = false)
    private boolean isUserDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town_id", referencedColumnName = "id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "currently_living_town_id", referencedColumnName = "id")
    private Town currentlyLivingTown;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Transient
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    private Set<User> friends;

    @OneToMany(mappedBy = "user")
    private Set<Album> albums;

    public User() {
    }

    public User(String username, String password, String email, LocalDateTime registrationDateAndTime, LocalDateTime lastLoginDateAndTime, int age, Town bornTown, Town currentlyLivingTown, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.registrationDateAndTime = registrationDateAndTime;
        this.lastLoginDateAndTime = lastLoginDateAndTime;
        this.age = age;
        this.isUserDeleted = false;
        this.bornTown = bornTown;
        this.currentlyLivingTown = currentlyLivingTown;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.friends = new HashSet<>();
        this.albums = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegistrationDateAndTime() {
        return registrationDateAndTime;
    }

    public LocalDateTime getLastLoginDateAndTime() {
        return lastLoginDateAndTime;
    }

    public int getAge() {
        return age;
    }

    public boolean isUserDeleted() {
        return isUserDeleted;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public Town getCurrentlyLivingTown() {
        return currentlyLivingTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setUserDeleted(boolean userDeleted) {
        isUserDeleted = userDeleted;
    }
}
