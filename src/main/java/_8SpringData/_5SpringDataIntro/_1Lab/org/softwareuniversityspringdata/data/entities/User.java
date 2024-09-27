package _8SpringData._5SpringDataIntro._1Lab.org.softwareuniversityspringdata.data.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "age")
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Account> accounts;

    public User() {
        this.accounts = new HashSet<>();
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.accounts = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
