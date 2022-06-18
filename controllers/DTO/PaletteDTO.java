package ir.equadesign.colorhunt.controllers.DTO;

import ir.equadesign.colorhunt.domain.Palette;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaletteDTO {
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 7)
    private String color1;

    @NotNull
    @NotBlank
    @Length(min = 7)
    private String color2;

    @NotNull
    @NotBlank
    @Length(min = 7)
    private String color3;

    @NotNull
    @NotBlank
    @Length(min = 7)
    private String color4;
    private Integer likes;
    private Instant creationDate;
    private Long categoryId;

    public PaletteDTO(Palette palette) {
        this.id = palette.getId();
        this.color1 = palette.getColor1();
        this.color2 = palette.getColor2();
        this.color3 = palette.getColor3();
        this.color4 = palette.getColor4();
        this.likes = palette.getLikes();
        this.creationDate = palette.getCreationDate();
        this.categoryId = palette.getCategory().getId();
    }
}
