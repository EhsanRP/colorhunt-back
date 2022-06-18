package ir.equadesign.colorhunt.services;

import ir.equadesign.colorhunt.controllers.DTO.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAllCategories();

    List<CategoryDTO> findAllCategoriesByApproved(Boolean approval);

    CategoryDTO findByNameAndApproved(String categoryName, Boolean approval);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long categoryId);

    CategoryDTO submitApproval(Long categoryId);

}
