package ir.equadesign.colorhunt.services;

import ir.equadesign.colorhunt.controllers.DTO.PaletteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaletteService {

    Page<PaletteDTO> findAllPalettesByApproval(Boolean approval, Pageable pageable);

    Page<PaletteDTO> findAllPalettesByCategoryIdAndIsApproved(Long categoryId,Boolean isApproved, Pageable pageable);

    Page<PaletteDTO> findAllByCategoryNameAndIsApproved(String categoryName, Boolean isApproved, Pageable pageable);

    Page<PaletteDTO> findAllPopularPalettes(Pageable pageable);

    Page<PaletteDTO> findRandomPalettes(Pageable pageable);

    List<PaletteDTO> findAllPalettesByIdList(List<Long> idList);

    PaletteDTO findByID(Long paletteId);

    PaletteDTO submitApproval(Long paletteId);

    PaletteDTO updatePalette(PaletteDTO paletteDTO);

    PaletteDTO savePalette(PaletteDTO paletteDTO);

    void likePalette(Long paletteId);

    void dislikePalette(Long paletteId);

    void deletePalette(Long paletteId);
}
