package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class represents the many-to-many relationship between a brew and a miscellaneous ingredient. This is not a simple join
 * table -- rather, it contains the information about that miscellaneous ingredient in the specific brew (e.g. amount).
 * It requires an ID as a primary key because a brew is able to have multiple entries with the same ingredient.
 */
@Entity(name="BrewMisc")
@Table(name = "brew_misc")
public class BrewMisc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
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

    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Misc misc;

    /**
     * Instantiates a new Brew misc.
     */
    public BrewMisc() {}

    /**
     * Instantiates a new Brew misc.
     *
     * @param amount              the amount
     * @param amountUnitOfMeasure the amount unit of measure
     * @param additionParameter   the addition parameter
     * @param timeInBrew          the time in brew
     * @param timeUnitOfMeasure   the time unit of measure
     */
    public BrewMisc(double amount, String amountUnitOfMeasure, String additionParameter, double timeInBrew, String timeUnitOfMeasure) {
        this.amount = amount;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
        this.additionParameter = additionParameter;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
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
     * Gets misc.
     *
     * @return the misc
     */
    public Misc getMisc() {
        return misc;
    }

    /**
     * Sets misc.
     *
     * @param misc the misc
     */
    public void setMisc(Misc misc) {
        this.misc = misc;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets amount unit of measure.
     *
     * @return the amount unit of measure
     */
    public String getAmountUnitOfMeasure() {
        return amountUnitOfMeasure;
    }

    /**
     * Sets amount unit of measure.
     *
     * @param amountUnitOfMeasure the amount unit of measure
     */
    public void setAmountUnitOfMeasure(String amountUnitOfMeasure) {
        this.amountUnitOfMeasure = amountUnitOfMeasure;
    }

    /**
     * Gets addition parameter.
     *
     * @return the addition parameter
     */
    public String getAdditionParameter() {
        return additionParameter;
    }

    /**
     * Sets addition parameter.
     *
     * @param additionParameter the addition parameter
     */
    public void setAdditionParameter(String additionParameter) {
        this.additionParameter = additionParameter;
    }

    /**
     * Gets time in brew.
     *
     * @return the time in brew
     */
    public double getTimeInBrew() {
        return timeInBrew;
    }

    /**
     * Sets time in brew.
     *
     * @param timeInBrew the time in brew
     */
    public void setTimeInBrew(double timeInBrew) {
        this.timeInBrew = timeInBrew;
    }

    /**
     * Gets time unit of measure.
     *
     * @return the time unit of measure
     */
    public String getTimeUnitOfMeasure() {
        return timeUnitOfMeasure;
    }

    /**
     * Sets time unit of measure.
     *
     * @param timeUnitOfMeasure the time unit of measure
     */
    public void setTimeUnitOfMeasure(String timeUnitOfMeasure) {
        this.timeUnitOfMeasure = timeUnitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrewMisc brewMisc = (BrewMisc) o;
        return id == brewMisc.id &&
                Double.compare(brewMisc.amount, amount) == 0 &&
                Double.compare(brewMisc.timeInBrew, timeInBrew) == 0 &&
                Objects.equals(amountUnitOfMeasure, brewMisc.amountUnitOfMeasure) &&
                Objects.equals(additionParameter, brewMisc.additionParameter) &&
                Objects.equals(timeUnitOfMeasure, brewMisc.timeUnitOfMeasure) &&
                Objects.equals(misc, brewMisc.misc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, amountUnitOfMeasure, additionParameter, timeInBrew, timeUnitOfMeasure, misc);
    }
}
