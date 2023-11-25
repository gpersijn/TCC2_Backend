package net.javaguides.springboot.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ExameResponseDTO {

    protected Integer idExame;

    protected String nomeExame;

    protected StatusExameEnum statusExame;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataExame;

    protected TipoExameEnum tipoExame;

    @JsonFormat(pattern = "HH:mm")
    protected LocalTime horaExame;

    protected String localExame;

    protected Pessoa pessoa;

    public ExameResponseDTO(Integer idExame, String nomeExame, StatusExameEnum statusExame, LocalDate dataExame, TipoExameEnum tipoExame, LocalTime horaExame, String localExame, Pessoa pessoa) {
        super();
        this.idExame = idExame;
        this.nomeExame = nomeExame;
        this.statusExame = statusExame;
        this.dataExame = dataExame;
        this.tipoExame = tipoExame;
        this.horaExame = horaExame;
        this.localExame = localExame;
        this.pessoa = pessoa;
    }

    public ExameResponseDTO(Exame campanha) {
        super();
        this.idExame = campanha.getIdExame();
        this.nomeExame = campanha.getNomeExame();
        this.statusExame = campanha.getStatusExame();
        this.dataExame = campanha.getDataExame();
        this.tipoExame = campanha.getTipoExame();
        this.horaExame = campanha.getHoraExame();
        this.localExame = campanha.getLocalExame();
        this.pessoa = campanha.getPessoa();
    }
}
