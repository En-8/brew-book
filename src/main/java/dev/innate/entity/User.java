package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "User")
@Table(name = "user")
public class User {
    @Column(name = "user_name")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "email")
    String email;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Brew> brews = new HashSet<>();

    public User() {

    }

    public User (String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Brew> getBrews() {
        return brews;
    }

    public void setBrews(Set<Brew> brews) {
        this.brews = brews;
    }

    public void addBrew(Brew brew) {
        brews.add(brew);
        brew.setUser(this);
    }

    public void removeBrew(Brew brew) {
        brews.remove(brew);
        brew.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                username.equals(user.username) &&
                password.equals(user.password) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, email, id);
    }
}
