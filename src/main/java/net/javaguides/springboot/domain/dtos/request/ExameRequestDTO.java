package net.javaguides.springboot.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.TipoASOEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ExameRequestDTO {

    protected Integer idExame;

    @NotNull(message = "O NOME EXAME deve ser preenchido.")
    @NotBlank(message = "O campo NOME EXAME não pode estar em branco.")
    protected String nomeExame;

    protected StatusExameEnum statusExame;

    @NotNull(message = "O campo data do exame deve ser preenchido.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataExame;

    protected TipoExameEnum tipoExame;

    @NotNull(message = "O NOME EXAME deve ser preenchido.")
    @JsonFormat(pattern = "HH:mm")
    protected LocalTime horaExame;

    @NotNull(message = "O LOCAL EXAME deve ser preenchido.")
    @NotBlank(message = "O campo LOCAL EXAME não pode estar em branco.")
    protected String localExame;

    protected Integer idPessoa;

    public ExameRequestDTO(Integer idExame, String nomeExame, StatusExameEnum statusExame, LocalDate dataExame, TipoExameEnum tipoExame, LocalTime horaExame, String localExame, Integer idPessoa) {
        this.idExame = idExame;
        this.nomeExame = nomeExame;
        this.statusExame = statusExame;
        this.dataExame = dataExame;
        this.tipoExame = tipoExame;
        this.horaExame = horaExame;
        this.localExame = localExame;
        this.idPessoa = idPessoa;
    }

}
