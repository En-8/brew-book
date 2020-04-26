package dev.innate.entity;

import javax.persistence.*;

@Entity(name="BrewMisc")
@Table(name = "brew_other_ingredient")
@AssociationOverrides({
        @AssociationOverride(name="pk.brew",
                joinColumns = @JoinColumn(name="brew_id")),
        @AssociationOverride(name="pk.other_ingredient",
                joinColumns = @JoinColumn(name="other_ingredient_id"))
})
public class BrewMisc {
    @EmbeddedId
    private BrewMiscId pk = new BrewMiscId();
    @Column(name="amount")
    private double amount;
    @Column(name="amount_unit_of_measure")
    private String amountUnitOfMeasure;
    @Column(name = "addition_parameter")
    private String additionParameter;
    @Column(name="time_in_brew")
    private double timeInBrew;
    @Column(name="time_unit_of_measure")
    private String timeUnitOfMeasure;

    public BrewMisc() {}

    public BrewMisc(BrewMiscId pk, double amount, String amountUnitOfMeasure, String additionParameter, double timeInBrew, String timeUnitOfMeasure) {
        this.pk = pk;
        this.amount = amount;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
        this.additionParameter = additionParameter;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
    }

    public BrewMiscId getPk() {
        return pk;
    }

    public void setPk(BrewMiscId pk) {
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
    public Miscellaneous getMisc() {
        return getPk().getMiscellaneous();
    }

    public void setMisc(Miscellaneous misc) {
        getPk().setMiscellaneous(misc);
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountUnitOfMeasure() {
        return amountUnitOfMeasure;
    }

    public void setAmountUnitOfMeasure(String amountUnitOfMeasure) {
        this.amountUnitOfMeasure = amountUnitOfMeasure;
    }

    public String getAdditionParameter() {
        return additionParameter;
    }

    public void setAdditionParameter(String additionParameter) {
        this.additionParameter = additionParameter;
    }

    public double getTimeInBrew() {
        return timeInBrew;
    }

    public void setTimeInBrew(double timeInBrew) {
        this.timeInBrew = timeInBrew;
    }

    public String getTimeUnitOfMeasure() {
        return timeUnitOfMeasure;
    }

    public void setTimeUnitOfMeasure(String timeUnitOfMeasure) {
        this.timeUnitOfMeasure = timeUnitOfMeasure;
    }
}
