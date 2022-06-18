package ir.equadesign.colorhunt.services;

import ir.equadesign.colorhunt.controllers.DTO.CategoryDTO;
import ir.equadesign.colorhunt.converters.CategoryConverter;
import ir.equadesign.colorhunt.exceptions.BadParameterException;
import ir.equadesign.colorhunt.repositories.CategoryRepository;
import ir.equadesign.colorhunt.validations.CategoryValidation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Value
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryValidation categoryValidation;
    CategoryConverter categoryConverter;

    //TODO - ADD TO ADMIN CATEGORY
    @Override
    public List<CategoryDTO> findAllCategories() {
        var categories = categoryRepository.findAll();
        return categoryConverter.dtoListMaker(categories);
    }

    @Override
    public List<CategoryDTO> findAllCategoriesByApproved(Boolean approval) {
        var categories = categoryRepository.findAllByIsApproved(approval);
        return categoryConverter.dtoListMaker(categories);
    }

    @Override
    public CategoryDTO findByNameAndApproved(String categoryName, Boolean approval) {
        var categoryOptional = categoryRepository.findByNameIgnoreCaseAndIsApproved(categoryName, approval);
        var category = categoryValidation.validateByField(categoryOptional, "name", categoryName);
        return categoryConverter.dtoMaker(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() == null)
            throw new BadParameterException("Category To update should have an ID attribute");

        var newCategory = categoryConverter.entityMaker(categoryDTO);
        var savedCategory = categoryRepository.save(newCategory);
        return categoryConverter.dtoMaker(savedCategory);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null)
            throw new BadParameterException("Category To create should not have an ID attribute");

        var category = categoryConverter.entityMaker(categoryDTO);
        category = categoryRepository.save(category);

        return categoryConverter.dtoMaker(category);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        var category = categoryValidation.validateID(categoryId);
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO submitApproval(Long categoryId) {
        var category = categoryValidation.validateID(categoryId);
        category.setIsApproved(!category.getIsApproved());
        category = categoryRepository.save(category);
        return categoryConverter.dtoMaker(category);
    }
}
