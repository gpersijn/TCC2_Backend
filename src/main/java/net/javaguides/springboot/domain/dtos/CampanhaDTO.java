package net.javaguides.springboot.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Campanha;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CampanhaDTO {
    protected Integer idCampanha;

    @NotNull(message = "O campo nome da campanha deve ser preenchido.")
    @NotBlank(message = "O campo nome da campanha não pode estar em branco.")
    protected String nomeCampanha;

    @NotNull(message = "O campo nome da vacina deve ser preenchido.")
    @NotBlank(message = "O campo nome da vacina não pode estar em branco.")
    protected String nomeVacina;

    @NotNull(message = "O campo data da campanha deve ser preenchido.")
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCampanha;

    @NotNull(message = "O campo descricao deve ser preenchido.")
    @NotBlank(message = "O campo descricao não pode estar em branco.")
    protected String descricao;

    public CampanhaDTO(Campanha campanha){
        super();
        this.idCampanha = campanha.getId();
        this.nomeCampanha = campanha.getNomeCampanha();
        this.nomeVacina = campanha.getNomeVacina();
        this.dataCampanha = campanha.getDataCampanha();
        this.descricao = campanha.getDescricao();
    }
}
