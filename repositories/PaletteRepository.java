package ir.equadesign.colorhunt.repositories;

import ir.equadesign.colorhunt.domain.Palette;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaletteRepository extends PagingAndSortingRepository<Palette, Long> {

    Page<Palette> findAllByIsApproved(Boolean isApproved, Pageable pageable);

    Page<Palette> findAllByIdIn(List<Long> idList, Pageable pageable);

    Page<Palette> findAllByCategory_NameIgnoreCaseAndIsApproved(String name, Boolean isApproved, Pageable pageable);

    @Query(nativeQuery = true, value = "Select * from Palette order by random()")
    Page<Palette> findAllRandom(Pageable pageable);

    Page<Palette> findAllByIsApprovedAndCategory_Id(Boolean isApproved, Long categoryId, Pageable pageable);

}
