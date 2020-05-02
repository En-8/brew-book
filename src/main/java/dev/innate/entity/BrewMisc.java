package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name="BrewMisc")
@Table(name = "brew_misc")
@AssociationOverrides({
        @AssociationOverride(name="pk.brew",
                joinColumns = @JoinColumn(name="brew_id")),
        @AssociationOverride(name="pk.misc",
                joinColumns = @JoinColumn(name="misc_id"))
})
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

    public BrewMisc() {}

    public BrewMisc(double amount, String amountUnitOfMeasure, String additionParameter, double timeInBrew, String timeUnitOfMeasure) {
        this.amount = amount;
        this.amountUnitOfMeasure = amountUnitOfMeasure;
        this.additionParameter = additionParameter;
        this.timeInBrew = timeInBrew;
        this.timeUnitOfMeasure = timeUnitOfMeasure;
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

    public Misc getMisc() {
        return misc;
    }

    public void setMisc(Misc misc) {
        this.misc = misc;
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
