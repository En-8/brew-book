package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="BrewFermentable")
@Table(name = "brew_fermentable")
public class BrewFermentable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
    @Column(name="amount")
    private float amount;
    @Column(name="unit_of_measure")
    private String unitOfMeasure;

    @ManyToOne
    private Brew brew;

    @ManyToOne
    private Fermentable fermentable;

    public BrewFermentable() {
    }

    public BrewFermentable(float amount, String unitOfMeasure) {
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
