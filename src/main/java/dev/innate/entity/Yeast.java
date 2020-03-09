package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="Yeast")
@Table(name="yeast")
public class Yeast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
    private String brand;
    private String name;

    @OneToMany(mappedBy = "yeast", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Brew> brews = new HashSet<>();

    public Yeast() {

    }

    public Yeast(String brand, String name, Brew brew) {
        this.brand = brand;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBrew(Brew brew) {
        brews.add(brew);
        brew.setYeast(this);
    }

    public void removeBrew(Brew brew) {
        brews.remove(brew);
        brew.setYeast(null);
    }
}
