package dev.innate.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BrewMiscId implements Serializable {
    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Misc misc;

    public BrewMiscId() {

    }

    public BrewMiscId(Brew brew, Misc misc) {
        this.brew = brew;
        this.misc = misc;
    }

    public Brew getBrew() {
        return brew;
    }

    public void setBrew(Brew brew) {
        this.brew = brew;
    }

    public Misc getMisc() {
        return misc;
    }

    public void setMisc(Misc misc) {
        this.misc = misc;
    }
}
