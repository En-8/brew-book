package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class represents the many-to-many relationship between a brew and a fermentable. This is not a simple join
 * table -- rather, it contains the information about that fermentable in the specific brew (e.g. amount). It requires
 * and ID as a primary key because a brew is able to have multiple entries with the same fermentable.
 */
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

    /**
     * Instantiates a new BrewFermentable.
     */
    public BrewFermentable() {
    }

    /**
     * Instantiates a new Brew fermentable.
     *
     * @param amount        the amount
     * @param unitOfMeasure the unit of measure
     */
    public BrewFermentable(float amount, String unitOfMeasure) {
        this.amount = amount;
        this.unitOfMeasure = unitOfMeasure;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets brew.
     *
     * @return the brew
     */
    public Brew getBrew() {
        return brew;
    }

    /**
     * Sets brew.
     *
     * @param brew the brew
     */
    public void setBrew(Brew brew) {
        this.brew = brew;
    }

    /**
     * Gets fermentable.
     *
     * @return the fermentable
     */
    public Fermentable getFermentable() {
        return fermentable;
    }

    /**
     * Sets fermentable.
     *
     * @param fermentable the fermentable
     */
    public void setFermentable(Fermentable fermentable) {
        this.fermentable = fermentable;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(float amount) {
        this.amount = amount;
    }

    /**
     * Gets unit of measure.
     *
     * @return the unit of measure
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets unit of measure.
     *
     * @param unitOfMeasure the unit of measure
     */
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrewFermentable that = (BrewFermentable) o;
        return id == that.id &&
                Float.compare(that.amount, amount) == 0 &&
                Objects.equals(unitOfMeasure, that.unitOfMeasure) &&
                Objects.equals(fermentable, that.fermentable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, unitOfMeasure, fermentable);
    }
}
