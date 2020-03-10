package dev.innate.entity;

import javax.persistence.*;

@Entity(name="BrewFermentable")
@Table(name = "brew_fermentable")
@AssociationOverrides({
        @AssociationOverride(name="pk.brew",
        joinColumns = @JoinColumn(name="brew_id")),
        @AssociationOverride(name="pk.fermentable",
        joinColumns = @JoinColumn(name="fermentable_id"))
})
public class BrewFermentable {
    @EmbeddedId
    private BrewFermentableId pk = new BrewFermentableId();
    @Column(name="amount")
    private float amount;
    @Column(name="unit_of_measure")
    private String unitOfMeasure;

    public BrewFermentable() {
    }

    public BrewFermentable(BrewFermentableId pk, float amount, String unitOfMeasure) {
        this.pk = pk;
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public BrewFermentableId getPk() {
        return pk;
    }

    public void setPk(BrewFermentableId pk) {
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
    public Fermentable getFermentable() {
        return getPk().getFermentable();
    }

    public void setFermentable(Fermentable fermentable) {
        getPk().setFermentable(fermentable);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    // TODO add equals and hashcode
}
