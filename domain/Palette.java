package ir.equadesign.colorhunt.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Palette{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String color1;
    private String color2;
    private String color3;
    private String color4;

    private Integer likes = 0;

    private Instant creationDate = Instant.now();

    private Boolean isApproved = false;

    @ManyToOne
    private Category category;

    public Palette(String color1, String color2, String color3, String color4) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        if (this.likes > 0)
            likes--;
    }
}
