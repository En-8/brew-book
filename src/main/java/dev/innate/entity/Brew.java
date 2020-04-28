package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @OneToMany(mappedBy = "pk.brew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BrewFermentable> brewFermentables = new HashSet<>();
    @OneToMany(mappedBy = "pk.brew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BrewHop> brewHops = new HashSet<>();
    @OneToMany(mappedBy = "pk.brew", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BrewMisc> brewMiscSet = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    public Brew() {
    }

    public Brew (String brewName, String description, String waterNotes, String pitchNotes, Yeast yeast, Style style, User user) {
        this.brewName = brewName;
        this.description = description;
        this.waterNotes = waterNotes;
        this.pitchNotes = pitchNotes;
        this.yeast = yeast;
        this.style = style;
        this.user = user;
    }

    public String getBrewName() {
        return brewName;
    }

    public void setBrewName(String brewName) {
        this.brewName = brewName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWaterNotes() {
        return waterNotes;
    }

    public void setWaterNotes(String waterNotes) {
        this.waterNotes = waterNotes;
    }

    public String getPitchNotes() {
        return pitchNotes;
    }

    public void setPitchNotes(String pitchNotes) {
        this.pitchNotes = pitchNotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Yeast getYeast() {
        return yeast;
    }

    public void setYeast(Yeast yeast) {
        this.yeast = yeast;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void addBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.add(brewFermentable);
        brewFermentable.setBrew(this);
    }

    public void removeBrewFermentable(BrewFermentable brewFermentable) {
        brewFermentables.remove(brewFermentable);
        brewFermentable.setBrew(null);
    }

    public void addBrewHop(BrewHop brewHop) {
        brewHops.add(brewHop);
        brewHop.setBrew(this);
    }

    public void removeBrewHop(BrewHop brewHop) {
        brewHops.remove(brewHop);
        brewHop.setBrew(null);
    }

    public void addBrewMisc(BrewMisc brewMisc) {
        brewMiscSet.add(brewMisc);
        brewMisc.setBrew(this);
    }

    public void removeBrewMisc(BrewMisc brewMisc) {
        brewMiscSet.remove(brewMisc);
        brewMisc.setBrew(null);
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
                style.equals(brew.style);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brewName, description, waterNotes, pitchNotes, user, yeast, style, id);
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
