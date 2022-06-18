package ir.equadesign.colorhunt.services;

import ir.equadesign.colorhunt.controllers.DTO.PaletteDTO;
import ir.equadesign.colorhunt.converters.PaletteConverter;
import ir.equadesign.colorhunt.repositories.CategoryRepository;
import ir.equadesign.colorhunt.repositories.PaletteRepository;
import ir.equadesign.colorhunt.validations.CategoryValidation;
import ir.equadesign.colorhunt.validations.PaletteValidation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Value
@RequiredArgsConstructor
@Service
public class PaletteServiceImpl implements PaletteService {

    PaletteValidation paletteValidation;

    CategoryRepository categoryRepository;
    CategoryValidation categoryValidation;
    PaletteRepository paletteRepository;
    PaletteConverter paletteConverter;

    @Override
    public Page<PaletteDTO> findAllPalettesByApproval(Boolean approval, Pageable pageable) {
        var palettes = paletteRepository.findAllByIsApproved(approval, pageable);
        return paletteConverter.pageToDTO(palettes);
    }

    @Override
    public Page<PaletteDTO> findAllPalettesByCategoryIdAndIsApproved(Long categoryId, Boolean isApproved, Pageable pageable) {
        categoryValidation.validateID(categoryId);

        var palettes = paletteRepository.findAllByIsApprovedAndCategory_Id(true,categoryId, pageable);
        return paletteConverter.pageToDTO(palettes);
    }

    @Override
    public Page<PaletteDTO> findAllByCategoryNameAndIsApproved(String categoryName, Boolean isApproved, Pageable pageable) {
        categoryValidation.validateByField(categoryRepository.findByNameIgnoreCase(categoryName), "name", categoryName);

        var palettes = paletteRepository.findAllByCategory_NameIgnoreCaseAndIsApproved(categoryName, isApproved, pageable);
        return paletteConverter.pageToDTO(palettes);
    }

    @Override
    public Page<PaletteDTO> findAllPopularPalettes(Pageable pageable) {
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("likes").descending());
        var palettes = paletteRepository.findAll(pageRequest);
        return paletteConverter.pageToDTO(palettes);
    }

    @Override
    public Page<PaletteDTO> findRandomPalettes(Pageable pageable) {
        var palettes = paletteRepository.findAllRandom(pageable);
        return paletteConverter.pageToDTO(palettes);
    }

    @Override
    public PaletteDTO findByID(Long paletteId) {
        var palette = paletteValidation.validateID(paletteId);
        return paletteConverter.dtoMaker(palette);
    }

    @Override
    public PaletteDTO submitApproval(Long paletteId) {
        var palette = paletteValidation.validateID(paletteId);
        return paletteConverter.dtoMaker(palette);
    }

    @Override
    public PaletteDTO updatePalette(PaletteDTO paletteDTO) {
        var newPalette = paletteConverter.entityMaker(paletteDTO);
        newPalette = paletteRepository.save(newPalette);
        return paletteConverter.dtoMaker(newPalette);
    }

    @Override
    public PaletteDTO savePalette(PaletteDTO paletteDTO) {
        var newPalette = paletteConverter.entityMaker(paletteDTO);
        newPalette = paletteRepository.save(newPalette);
        return paletteConverter.dtoMaker(newPalette);
    }

    @Override
    public void likePalette(Long paletteId) {
        var palette = paletteValidation.validateID(paletteId);
        palette.increaseLikes();
        paletteRepository.save(palette);
    }

    @Override
    public void dislikePalette(Long paletteId) {
        var palette = paletteValidation.validateID(paletteId);
        palette.decreaseLikes();
        paletteRepository.save(palette);
    }

    @Override
    public void deletePalette(Long paletteId) {
        var palette = paletteValidation.validateID(paletteId);
        paletteRepository.delete(palette);
    }
}
