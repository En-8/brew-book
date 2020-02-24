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

    // TODO these are other many-to-one relationships that need to be dealt with.
    @Column(name = "yeast_id")
    private int yeastId;
    @Column(name = "style_id")
    private int styleId;

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
        this.yeastId = yeastId;
        this.styleId = styleId;
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

    public int getYeastId() {
        return yeastId;
    }

    public void setYeastId(int yeastId) {
        this.yeastId = yeastId;
    }

    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brew brew = (Brew) o;
        return yeastId == brew.yeastId &&
                styleId == brew.styleId &&
                id == brew.id &&
                brewName.equals(brew.brewName) &&
                description.equals(brew.description) &&
                waterNotes.equals(brew.waterNotes) &&
                pitchNotes.equals(brew.pitchNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brewName, description, waterNotes, pitchNotes, yeastId, styleId, id);
    }
}
