package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    public BrewHop() {
    }

    public BrewHop(double amount, String method, double timeInBrew, String timeUnitOfMeasure, String amountUnitOfMeasure) {
        this.amount = amount;
        this.method = method;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
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

    public Hop getHop() {
        return hop;
    }

    public void setHop(Hop hop) {
        this.hop = hop;
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
