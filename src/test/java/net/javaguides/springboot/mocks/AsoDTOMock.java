package net.javaguides.springboot.mocks;

import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;

import java.time.LocalDate;
import java.util.List;

public class AsoDTOMock {
    public static ASORequestDTO withDefaultValuesRequest() {
        ASORequestDTO dto = new ASORequestDTO();
        dto.setIdASO(1);
        dto.setIdPessoa(1);
        dto.setCnpj("1234567890");
        dto.setNomeEmpresa("Empresa XYZ");
        dto.setRisco(List.of("Bactéias"));
        dto.setExames(List.of(1, 2, 3)); // IDs de exames
        dto.setNomeMedicoPCMSO("Dr. PCMSO");
        dto.setCrmMedicoPCMSO("12345");
        dto.setNomeMedicoClinico("Dr. Clínico");
        dto.setCrmMedicoClinico("67890");
        dto.setResultadoASO(ResultadoASOEnum.APTO);
        dto.setValidade(LocalDate.of(2023, 12, 31));
        dto.setDataASO(LocalDate.of(2023, 11, 30));
        return dto;
    }
}
