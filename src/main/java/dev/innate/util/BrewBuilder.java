package dev.innate.util;

import dev.innate.entity.*;

/**
 * This class provides a fluent interface for creating an instance of Brew. All of the fields correspond to the
 * available fields of an instance of Brew, and every method (except build) returns the instance of the builder
 * it was called on, allowing for method chaining. Once all of the desired fields have been set, calling build() returns
 * an instance of Brew with those fields populated with the specified values.
 */
public class BrewBuilder {
    private String name;
    private String description;
    private String waterNotes;
    private String pitchNotes;
    private User user;
    private Yeast yeast;
    private Style style;

    /**
     * Assigns a name to the brew this instance of builder is creating
     *
     * @param name the name
     * @return the brew builder
     */
    public BrewBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Assigns a description to the brew this instance of builder is creating
     *
     * @param description the description
     * @return the brew builder
     */
    public BrewBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the brew water notes
     *
     * @param waterNotes the water notes
     * @return the brew builder
     */
    public BrewBuilder withWaterNotes(String waterNotes) {
        this.waterNotes = waterNotes;
        return this;
    }

    /**
     * Sets the brew yeast pitch notes
     *
     * @param pitchNotes the pitch notes
     * @return the brew builder
     */
    public BrewBuilder withPitchNotes(String pitchNotes) {
        this.pitchNotes = pitchNotes;
        return this;
    }

    /**
     * Specifies which user this brew belongs to
     *
     * @param user the user
     * @return the brew builder
     */
    public BrewBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    /**
     * Sets the yeast
     *
     * @param yeast the yeast
     * @return the brew builder
     */
    public BrewBuilder withYeast(Yeast yeast) {
        this.yeast = yeast;
        return this;
    }

    /**
     * Sets the style.
     *
     * @param style the style
     * @return the brew builder
     */
    public BrewBuilder withStyle(Style style) {
        this.style = style;
        return this;
    }

    /**
     * Creates an instance of Brew with the fields specified at the time build is called.
     *
     * @return the brew
     */
    public Brew build() {
        return new Brew(this.name, this.description, this.waterNotes, this.pitchNotes, this.yeast, this.style, this.user);
    }
}
