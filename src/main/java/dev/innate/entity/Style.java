package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents a style that a particular brew could be e.g. Pilsner
 */
@Entity(name="Style")
@Table(name="style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
    @Column(name="style_name")
    private String name;
    /**
     * The brews that belong to this style.
     */
    @OneToMany(mappedBy = "style", fetch = FetchType.LAZY)
    Set<Brew> brews = new HashSet<>();

    /**
     * Instantiates a new Style.
     */
    public Style() {}

    /**
     * Instantiates a new Style.
     *
     * @param name the name
     */
    public Style(String name) {
        this.name = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Add brew.
     *
     * @param brew the brew
     */
    public void addBrew(Brew brew) {
        brews.add(brew);
        brew.setStyle(this);
    }

    /**
     * Remove brew.
     *
     * @param brew the brew
     */
    public void removeBrew(Brew brew) {
        brews.remove(brew);
        brew.setStyle(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Style style = (Style) o;
        return id == style.id &&
                Objects.equals(name, style.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Style{" +
                "id=" + id +
                ", styleName='" + name + '\'' +
                '}';
    }
}
