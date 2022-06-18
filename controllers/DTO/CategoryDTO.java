package ir.equadesign.colorhunt.controllers.DTO;

import ir.equadesign.colorhunt.domain.Category;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoryDTO {

    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 3)
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
