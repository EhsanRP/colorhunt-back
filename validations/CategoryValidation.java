package ir.equadesign.colorhunt.validations;

import ir.equadesign.colorhunt.domain.Category;
import ir.equadesign.colorhunt.exceptions.ResourceNotFoundException;

import java.util.Optional;

public interface CategoryValidation {

    Category validateID(Long categoryId) throws ResourceNotFoundException;

    Category validateByField(Optional<Category> categoryOptional, String expectedParameterName, String expectedParameter);

}
