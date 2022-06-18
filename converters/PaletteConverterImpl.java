package ir.equadesign.colorhunt.converters;

import ir.equadesign.colorhunt.controllers.DTO.PaletteDTO;
import ir.equadesign.colorhunt.domain.Palette;
import ir.equadesign.colorhunt.exceptions.BadParameterException;
import ir.equadesign.colorhunt.validations.CategoryValidation;
import ir.equadesign.colorhunt.validations.PaletteValidation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Value
@RequiredArgsConstructor
@Service
public class PaletteConverterImpl implements PaletteConverter {

    CategoryValidation categoryValidation;
    PaletteValidation paletteValidation;

    @Override
    public Palette entityMaker(PaletteDTO paletteDTO) {

        var palette = new Palette();
        palette.setColor1(paletteDTO.getColor1());
        palette.setColor2(paletteDTO.getColor2());
        palette.setColor3(paletteDTO.getColor3());
        palette.setColor4(paletteDTO.getColor4());
        palette.setLikes(paletteDTO.getLikes() != null ? paletteDTO.getLikes() : 0);

        if (paletteDTO.getId() != null) {
            var existingPalette = paletteValidation.validateID(paletteDTO.getId());
            palette.setId(paletteDTO.getId());
            palette.setIsApproved(existingPalette.getIsApproved());
        }

        if (paletteDTO.getCategoryId() == null)
            throw new BadParameterException("Palette must have Category ID");

        var category = categoryValidation.validateID(paletteDTO.getCategoryId());
        palette.setCategory(category);

        return palette;
    }

    @Override
    public PaletteDTO dtoMaker(Palette palette) {
        return new PaletteDTO(palette);
    }

    @Override
    public List<PaletteDTO> dtoListMaker(List<Palette> palettes) {
        var list = new ArrayList<PaletteDTO>();

        palettes.forEach(palette -> list.add(dtoMaker(palette)));

        return list;
    }

    @Override
    public Page<PaletteDTO> pageToDTO(Page<Palette> palettes) {
        return palettes.map(this::dtoMaker);
    }
}
