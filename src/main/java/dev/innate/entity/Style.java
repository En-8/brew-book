package dev.innate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="Style")
@Table(name="style")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name="native", strategy = "native")
    private int id;
    @Column(name="style_name")
    private String styleName;
    @OneToMany(mappedBy = "style", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Brew> brews = new HashSet<>();

    public Style() {}

    public Style(String styleName) {
        this.styleName = styleName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStyleName() {
        return styleName;
    }

    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public void addBrew(Brew brew) {
        brews.add(brew);
        brew.setStyle(this);
    }

    public void removeBrew(Brew brew) {
        brews.remove(brew);
        brew.setStyle(null);
    }
}
