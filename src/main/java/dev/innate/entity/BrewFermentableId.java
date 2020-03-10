package dev.innate.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BrewFermentableId implements Serializable {
    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Fermentable fermentable;

    public BrewFermentableId() {

    }

    public BrewFermentableId(Brew brew, Fermentable fermentable) {
        this.brew = brew;
        this.fermentable = fermentable;
    }

    public Brew getBrew() {
        return brew;
    }

    public void setBrew(Brew brew) {
        this.brew = brew;
    }

    public Fermentable getFermentable() {
        return fermentable;
    }

    public void setFermentable(Fermentable fermentable) {
        this.fermentable = fermentable;
    }

    // TODO add equals and hashcode
}
