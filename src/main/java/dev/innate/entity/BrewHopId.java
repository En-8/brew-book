package dev.innate.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class BrewHopId implements Serializable {
    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Hop hop;

    public BrewHopId() {

    }

    public BrewHopId(Brew brew, Hop hop) {
        this.brew = brew;
        this.hop = hop;
    }

    public Brew getBrew() {
        return brew;
    }

    public void setBrew(Brew brew) {
        this.brew = brew;
    }

    public Hop getHop() {
        return hop;
    }

    public void setHop(Hop hop) {
        this.hop = hop;
    }

    // TODO add equals and hashcode
}
