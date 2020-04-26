package dev.innate.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BrewMiscId implements Serializable {
    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Miscellaneous miscellaneous;

    public BrewMiscId() {

    }

    public BrewMiscId(Brew brew, Miscellaneous miscellaneous) {
        this.brew = brew;
        this.miscellaneous = miscellaneous;
    }

    public Brew getBrew() {
        return brew;
    }

    public void setBrew(Brew brew) {
        this.brew = brew;
    }

    public Miscellaneous getMiscellaneous() {
        return miscellaneous;
    }

    public void setMiscellaneous(Miscellaneous miscellaneous) {
        this.miscellaneous = miscellaneous;
    }
}
