package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;

    public Brew() {
    }

    public Brew (String brewName, String description, String waterNotes, String pitchNotes, int yeastId, int styleId, User user) {
        this.brewName = brewName;
        this.description = description;
        this.waterNotes = waterNotes;
        this.pitchNotes = pitchNotes;
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
}
