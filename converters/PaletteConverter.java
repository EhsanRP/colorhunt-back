package ir.equadesign.colorhunt.converters;

import ir.equadesign.colorhunt.controllers.DTO.PaletteDTO;
import ir.equadesign.colorhunt.domain.Palette;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PaletteConverter {

    Palette entityMaker(PaletteDTO paletteDTO);

    PaletteDTO dtoMaker(Palette palette);

    List<PaletteDTO> dtoListMaker(List<Palette> palettes);

    Page<PaletteDTO> pageToDTO(Page<Palette> palettes);
}
