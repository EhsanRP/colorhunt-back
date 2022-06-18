package ir.equadesign.colorhunt.repositories;

import ir.equadesign.colorhunt.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);

    Optional<Category> findByNameIgnoreCaseAndIsApproved(String name, Boolean isApproved);

    List<Category> findAllByIsApproved(Boolean isApproved);
}
