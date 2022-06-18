package ir.equadesign.colorhunt.converters;

import ir.equadesign.colorhunt.controllers.DTO.CategoryDTO;
import ir.equadesign.colorhunt.domain.Category;

import java.util.List;

public interface CategoryConverter {
    Category entityMaker(CategoryDTO categoryDTO);

    CategoryDTO dtoMaker(Category category);

    List<CategoryDTO> dtoListMaker(List<Category> categories);
}
