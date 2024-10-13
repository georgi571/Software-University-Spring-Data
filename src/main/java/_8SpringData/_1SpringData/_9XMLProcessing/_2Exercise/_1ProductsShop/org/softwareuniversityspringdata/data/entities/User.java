package _8SpringData._1SpringData._9XMLProcessing._2Exercise._1ProductsShop.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private Set<Product> bought;

    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Product> sold;

    @ManyToMany
    @JoinTable(name = "users_friends",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id"))
    private Set<User> userFriends;

    public User() {
        this.bought = new HashSet<>();
        this.sold = new HashSet<>();
        this.userFriends = new HashSet<>();
    }

    public User(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.bought = new HashSet<>();
        this.sold = new HashSet<>();
        this.userFriends = new HashSet<>();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Product> getBought() {
        return bought;
    }

    public void setBought(Set<Product> boughtProducts) {
        this.bought = boughtProducts;
    }

    public Set<Product> getSold() {
        return sold;
    }

    public void setSold(Set<Product> sellingProducts) {
        this.sold = sellingProducts;
    }

    public Set<User> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(Set<User> userFriends) {
        this.userFriends = userFriends;
    }
}
