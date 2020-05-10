package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

/**
 * A single Brew, which represents a beer recipe.
 */
@Entity(name = "Brew")
@Table(name = "brew")
public class Brew {
    @Column(name = "name")
    private String brewName;
    private String description;
    @Column(name = "water_notes")
    private String waterNotes;
    @Column(name = "pitch_notes")
    private String pitchNotes;

    @ManyToOne
    private User user;
    @ManyToOne
    private Yeast yeast;
    @ManyToOne
    private Style style;

    @OneToMany(mappedBy = "brew", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BrewFermentable> brewFermentables;
    @OneToMany(mappedBy = "brew", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BrewHop> brewHops;
    @OneToMany(mappedBy = "brew", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<BrewMisc> brewMiscSet;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    /**
     * Instantiates a new Brew.
     */
    public Brew() {
        brewFermentables = new HashSet<>();
        brewHops = new HashSet<>();
        brewMiscSet = new HashSet<>();
    }

    /**
     * Instantiates a new Brew.
     *
     * @param brewName    the brew name
     * @param description the description
     * @param waterNotes  the water notes
     * @param pitchNotes  the pitch notes
     * @param yeast       the yeast
     * @param style       the style
     * @param user        the user
     */
    public Brew (String brewName, String description, String waterNotes, String pitchNotes, Yeast yeast, Style style, User user) {
        this();
        this.brewName = brewName;
        this.description = description;
        this.waterNotes = waterNotes;
        this.pitchNotes = pitchNotes;
        this.yeast = yeast;
        this.style = style;
        this.user = user;
    }

    /**
     * Gets brew name.
     *
     * @return the brew name
     */
    public String getBrewName() {
        return brewName;
    }

    /**
     * Sets brew name.
     *
     * @param brewName the brew name
     */
    public void setBrewName(String brewName) {
        this.brewName = brewName;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets water notes.
     *
     * @return the water notes
     */
    public String getWaterNotes() {
        return waterNotes;
    }

    /**
     * Sets water notes.
     *
     * @param waterNotes the water notes
     */
    public void setWaterNotes(String waterNotes) {
        this.waterNotes = waterNotes;
    }

    /**
     * Gets pitch notes.
     *
     * @return the pitch notes
     */
    public String getPitchNotes() {
        return pitchNotes;
    }

    /**
     * Sets pitch notes.
     *
     * @param pitchNotes the pitch notes
     */
    public void setPitchNotes(String pitchNotes) {
        this.pitchNotes = pitchNotes;
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
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets yeast.
     *
     * @return the yeast
     */
    public Yeast getYeast() {
        return yeast;
    }

    /**
     * Sets yeast.
     *
     * @param yeast the yeast
     */
    public void setYeast(Yeast yeast) {
        this.yeast = yeast;
    }

    /**
     * Gets style.
     *
     * @return the style
     */
    public Style getStyle() {
        return style;
    }

    /**
     * Sets style.
     *
     * @param style the style
     */
    public void setStyle(Style style) {
        this.style = style;
    }

    /**
     * Add brew fermentable.
     *
     * @param brewFermentable the brew fermentable
     */
    public void addBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.add(brewFermentable);
        brewFermentable.setBrew(this);
    }

    /**
     * Remove brew fermentable.
     *
     * @param brewFermentable the brew fermentable
     */
    public void removeBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.remove(brewFermentable);
        brewFermentable.setBrew(null);
    }

    /**
     * Add brew hop.
     *
     * @param brewHop the brew hop
     */
    public void addBrewHop(BrewHop brewHop) {
        brewHops.add(brewHop);
        brewHop.setBrew(this);
    }

    /**
     * Remove brew hop.
     *
     * @param brewHop the brew hop
     */
    public void removeBrewHop(BrewHop brewHop) {
        brewHops.remove(brewHop);
        brewHop.setBrew(null);
    }

    /**
     * Add brew misc.
     *
     * @param brewMisc the brew misc
     */
    public void addBrewMisc(BrewMisc brewMisc) {
        brewMiscSet.add(brewMisc);
        brewMisc.setBrew(this);
    }

    /**
     * Remove brew misc.
     *
     * @param brewMisc the brew misc
     */
    public void removeBrewMisc(BrewMisc brewMisc) {
        brewMiscSet.remove(brewMisc);
        brewMisc.setBrew(null);
    }

    /**
     * Gets brew fermentables.
     *
     * @return the brew fermentables
     */
    public Set<BrewFermentable> getBrewFermentables() {
        return brewFermentables;
    }

    /**
     * Gets brew hops.
     *
     * @return the brew hops
     */
    public Set<BrewHop> getBrewHops() {
        return brewHops;
    }

    /**
     * Gets brew misc set.
     *
     * @return the brew misc set
     */
    public Set<BrewMisc> getBrewMiscSet() {
        return brewMiscSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brew brew = (Brew) o;
        return id == brew.id &&
                brewName.equals(brew.brewName) &&
                Objects.equals(description, brew.description) &&
                Objects.equals(waterNotes, brew.waterNotes) &&
                Objects.equals(pitchNotes, brew.pitchNotes) &&
                user.equals(brew.user) &&
                Objects.equals(yeast, brew.yeast) &&
                style.equals(brew.style) &&
                Objects.equals(brewFermentables, brew.brewFermentables) &&
                Objects.equals(brewHops, brew.brewHops) &&
                Objects.equals(brewMiscSet, brew.brewMiscSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brewName, description, waterNotes, pitchNotes, user, yeast, style, brewFermentables, brewHops, brewMiscSet, id);
    }

    @Override
    public String toString() {
        return "Brew{" +
                "brewName='" + brewName + '\'' +
                ", description='" + description + '\'' +
                ", waterNotes='" + waterNotes + '\'' +
                ", pitchNotes='" + pitchNotes + '\'' +
                ", user=" + user +
                ", yeast=" + yeast +
                ", style=" + style +
                ", id=" + id +
                '}';
    }
}
