package net.javaguides.springboot.mocks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.request.ExameRequestDTO;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ExameDTOMock {
    public static ExameRequestDTO withRequestValues() {
        ExameRequestDTO dto = new ExameRequestDTO();
        dto.setIdExame(1);
        dto.setNomeExame("Exame de Amostra");
        dto.setStatusExame(StatusExameEnum.PENDENTE);
        dto.setDataExame(LocalDate.of(2023, 11, 30));
        dto.setTipoExame(TipoExameEnum.COMPLEMENTAR);
        dto.setHoraExame(LocalTime.of(14, 30));
        dto.setLocalExame("Laboratório XYZ");
        dto.setIdPessoa(2);
        return dto;
    }
}
