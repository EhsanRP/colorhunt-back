package ir.equadesign.colorhunt.controllers;

import ir.equadesign.colorhunt.controllers.DTO.CategoryDTO;
import ir.equadesign.colorhunt.domain.Category;
import ir.equadesign.colorhunt.repositories.CategoryRepository;
import ir.equadesign.colorhunt.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    CategoryService categoryService;
    CategoryRepository categoryRepository;

    @GetMapping("/test/{categoryId}")
    public Category test(@PathVariable String categoryId){
        return categoryRepository.findById(Long.valueOf(categoryId)).get();
    }

    @GetMapping("/all")
    public List<CategoryDTO> getCategoriesByApproval(@RequestParam boolean approval) {
        log.debug("Getting all the categories by approved value {}", approval);
        return categoryService.findAllCategoriesByApproved(approval);
    }

    @GetMapping("/get/{categoryName}")
    public CategoryDTO getCategoriesByNameAndApproval(@RequestParam boolean approval, @PathVariable String categoryName) {
        log.debug("Getting all the categories by approved value {}", approval);
        return categoryService.findByNameAndApproved(categoryName,approval);
    }

    @PutMapping("/update")
    public CategoryDTO updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.debug("Updating category with id {} and name {}", categoryDTO.getId(), categoryDTO.getName());

        return categoryService.updateCategory(categoryDTO);
    }

    @PostMapping("/create")
    public CategoryDTO saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        log.debug("Creating category with name {}", categoryDTO.getName());

        return categoryService.createCategory(categoryDTO);
    }

    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        log.debug("Deleting Category with id {}", categoryId);

        categoryService.deleteCategory(Long.valueOf(categoryId));
    }

    @PutMapping("/approval/{categoryId}")
    public CategoryDTO submitApproval(@PathVariable String categoryId){
        log.debug("Submitting approval status for Category with id {}", categoryId);

        return categoryService.submitApproval(Long.valueOf(categoryId));
    }

}
