package dev.innate.util;

import dev.innate.entity.*;

public class BrewBuilder {
    private String name;
    private String description;
    private String waterNotes;
    private String pitchNotes;
    private User user;
    private Yeast yeast;
    private Style style;

    public BrewBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BrewBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public BrewBuilder withWaterNotes(String waterNotes) {
        this.waterNotes = waterNotes;
        return this;
    }

    public BrewBuilder withPitchNotes(String pitchNotes) {
        this.pitchNotes = pitchNotes;
        return this;
    }

    public BrewBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public BrewBuilder withYeast(Yeast yeast) {
        this.yeast = yeast;
        return this;
    }

    public BrewBuilder withStyle(Style style) {
        this.style = style;
        return this;
    }

    public Brew build() {
        return new Brew(this.name, this.description, this.waterNotes, this.pitchNotes, this.yeast, this.style, this.user);
    }
}
