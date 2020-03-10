package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name="Fermentable")
@Table(name="fermentable")
public class Fermentable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
    private String name;

    @OneToMany(mappedBy = "pk.fermentable", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BrewFermentable> brewFermentables = new HashSet<>();

    public Fermentable() {
    }

    public Fermentable(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.add(brewFermentable);
        brewFermentable.setFermentable(this);
    }

    public void removeBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.remove(brewFermentable);
        brewFermentable.setFermentable(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fermentable that = (Fermentable) o;
        return id == that.id &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
