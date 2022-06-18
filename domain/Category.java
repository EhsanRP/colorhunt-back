package ir.equadesign.colorhunt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    private Boolean isApproved = false;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category",fetch = FetchType.EAGER)
    private Set<Palette> palettes = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void addPalette(Palette palette) {
        palette.setCategory(this);
        this.palettes.add(palette);
    }
}
