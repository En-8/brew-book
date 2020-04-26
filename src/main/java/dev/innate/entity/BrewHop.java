package dev.innate.entity;

import javax.persistence.*;

@Entity(name="BrewHop")
@Table(name = "brew_hop")
@AssociationOverrides({
        @AssociationOverride(name="pk.brew",
                joinColumns = @JoinColumn(name="brew_id")),
        @AssociationOverride(name="pk.hop",
                joinColumns = @JoinColumn(name="hop_id"))
})
public class BrewHop {
    @EmbeddedId
    private BrewHopId pk = new BrewHopId();
    @Column(name="amount")
    private double amount;
    @Column(name="method")
    private String method;
    @Column(name="time_in_brew")
    private double timeInBrew;
    @Column(name="time_unit_of_measure")
    private String timeUnitOfMeasure;
    @Column(name="amount_unit_of_measure")
    private String amountUnitOfMeasure;

    public BrewHop() {
    }

    public BrewHop(BrewHopId pk, double amount, String method, double timeInBrew, String timeUnitOfMeasure, String amountUnitOfMeasure) {
        this.pk = pk;
        this.amount = amount;
        this.method = method;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
    }

    public BrewHopId getPk() {
        return pk;
    }

    public void setPk(BrewHopId pk) {
        this.pk = pk;
    }

    @Transient
    public Brew getBrew() {
        return getPk().getBrew();
    }

    public void setBrew(Brew brew) {
        getPk().setBrew(brew);
    }

    @Transient
    public Hop getHop() {
        return getPk().getHop();
    }

    public void setHop(Hop hop) {
        getPk().setHop(hop);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTimeUnitOfMeasure() {
        return timeUnitOfMeasure;
    }

    public void setTimeUnitOfMeasure(String timeUnitOfMeasure) {
        this.timeUnitOfMeasure = timeUnitOfMeasure;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getTimeInBrew() {
        return timeInBrew;
    }

    public void setTimeInBrew(double timeInBrew) {
        this.timeInBrew = timeInBrew;
    }

    public String getAmountUnitOfMeasure() {
        return amountUnitOfMeasure;
    }

    public void setAmountUnitOfMeasure(String amountUnitOfMeasure) {
        this.amountUnitOfMeasure = amountUnitOfMeasure;
    }

    // TODO add equals and hashcode
}
