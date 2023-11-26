package net.javaguides.springboot.mocks;

import net.javaguides.springboot.domain.dtos.CampanhaDTO;

import java.time.LocalDate;

public class CampanhaDTOMock {
    public static CampanhaDTO withDefaultValues() {
        CampanhaDTO dto = new CampanhaDTO();
        dto.setIdCampanha(1);
        dto.setNomeCampanha("Campanha de Vacinação");
        dto.setNomeVacina("Vacina XYZ");
        dto.setDataCampanha(LocalDate.of(2023, 11, 30));
        dto.setDescricao("Campanha para vacinação em massa");
        return dto;
    }
}
