package ir.equadesign.colorhunt.controllers;

import ir.equadesign.colorhunt.controllers.DTO.PaletteDTO;
import ir.equadesign.colorhunt.services.PaletteService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Value
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/palettes")
public class PaletteController {

    PaletteService paletteService;

    @GetMapping("/all")
    public Page<PaletteDTO> findAllPalettes(@RequestParam int size, @RequestParam int page) {
        log.debug("Getting All Palettes");

        return paletteService.findAllPalettesByApproval(true, PageRequest.of(page, size));
    }

    @GetMapping("/all/category/{categoryId}")
    public Page<PaletteDTO> findAllByCategoryId(@PathVariable String categoryId, @RequestParam int size, @RequestParam int page) {
        log.debug("Getting Palettes With Category ID {}", categoryId);

        return paletteService.findAllPalettesByCategoryIdAndIsApproved(Long.valueOf(categoryId), true, PageRequest.of(page, size));
    }

    @GetMapping("/category/{categoryName}")
    public Page<PaletteDTO> findAllByCategoryName(@PathVariable String categoryName, @RequestParam int size, @RequestParam int page) {
        log.debug("Getting Palettes With Category Name {}", categoryName);

        return paletteService.findAllByCategoryNameAndIsApproved(categoryName, true, PageRequest.of(page, size));
    }

    @GetMapping("/popular")
    public Page<PaletteDTO> findAllPopularPalettes(@RequestParam int size, @RequestParam int page) {
        log.debug("Getting Popular Palettes");

        return paletteService.findAllPopularPalettes(PageRequest.of(page, size));
    }

    @GetMapping("/random")
    public Page<PaletteDTO> findRandomPalettes(@RequestParam int size, @RequestParam int page) {
        log.debug("Getting Random Palettes");

        return paletteService.findRandomPalettes(PageRequest.of(page, size));
    }

    @GetMapping("/id/{paletteId}")
    public PaletteDTO findPaletteById(@PathVariable String paletteId) {
        log.debug("Getting Palette With ID {}", paletteId);

        return paletteService.findByID(Long.valueOf(paletteId));
    }

    @PatchMapping("/approval/{paletteId}")
    public PaletteDTO submitApproval(@PathVariable String paletteId) {
        log.debug("Submitting Approval For Palette With ID {}", paletteId);

        return paletteService.submitApproval(Long.valueOf(paletteId));
    }

    @PatchMapping("/like/{paletteId}")
    public void likePalette(@PathVariable String paletteId) {
        log.debug("Liking Palette With ID {}", paletteId);

        paletteService.likePalette(Long.valueOf(paletteId));
    }

    @PatchMapping("/dislike/{paletteId}")
    public void dislikePalette(@PathVariable String paletteId) {
        log.debug("Disliking Palette With ID {}", paletteId);

        paletteService.dislikePalette(Long.valueOf(paletteId));
    }

    @PatchMapping("/update")
    public PaletteDTO updatePalette(@Valid @RequestBody PaletteDTO paletteDTO) {
        log.debug("Updating Palette With ID {}", paletteDTO.getId());

        return paletteService.updatePalette(paletteDTO);
    }

    @PostMapping("/new")
    public PaletteDTO savePalette(@Valid @RequestBody PaletteDTO paletteDTO) {
        log.debug("Creating A New Palette");

        return paletteService.savePalette(paletteDTO);
    }

    @DeleteMapping("/delete/{paletteId}")
    public void deletePalette(@PathVariable String paletteId) {
        log.debug("Deleting Palette With ID {}", paletteId);

        paletteService.deletePalette(Long.valueOf(paletteId));
    }
}
