package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class represents the many-to-many relationship between a brew and a hop. This is not a simple join
 * table -- rather, it contains the information about that hop in the specific brew (e.g. amount). It requires
 * and ID as a primary key because a brew is able to have multiple entries with the same hop.
 */
@Entity(name="BrewHop")
@Table(name = "brew_hop")
public class BrewHop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
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

    @ManyToOne
    private Brew brew;
    @ManyToOne
    private Hop hop;

    /**
     * Instantiates a new Brew hop.
     */
    public BrewHop() {
    }

    /**
     * Instantiates a new Brew hop.
     *
     * @param amount              the amount
     * @param method              the method
     * @param timeInBrew          the time in brew
     * @param timeUnitOfMeasure   the time unit of measure
     * @param amountUnitOfMeasure the amount unit of measure
     */
    public BrewHop(double amount, String method, double timeInBrew, String timeUnitOfMeasure, String amountUnitOfMeasure) {
        this.amount = amount;
        this.method = method;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
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
     * Gets hop.
     *
     * @return the hop
     */
    public Hop getHop() {
        return hop;
    }

    /**
     * Sets hop.
     *
     * @param hop the hop
     */
    public void setHop(Hop hop) {
        this.hop = hop;
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

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Sets method.
     *
     * @param method the method
     */
    public void setMethod(String method) {
        this.method = method;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrewHop brewHop = (BrewHop) o;
        return id == brewHop.id &&
                Double.compare(brewHop.amount, amount) == 0 &&
                Double.compare(brewHop.timeInBrew, timeInBrew) == 0 &&
                Objects.equals(method, brewHop.method) &&
                Objects.equals(timeUnitOfMeasure, brewHop.timeUnitOfMeasure) &&
                Objects.equals(amountUnitOfMeasure, brewHop.amountUnitOfMeasure) &&
                Objects.equals(hop, brewHop.hop);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, method, timeInBrew, timeUnitOfMeasure, amountUnitOfMeasure, hop);
    }
}
