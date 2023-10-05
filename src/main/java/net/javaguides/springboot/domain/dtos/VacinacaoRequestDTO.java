package net.javaguides.springboot.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
public class VacinacaoRequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    protected StatusVacinacaoEnum status;

    @NotNull(message = "Pelo menos um funcionário deve ser preenchido.")
    protected List<Integer> idFuncionarios;

    @NotNull(message = "Pelo menos um funcionário deve ser preenchido.")
    protected Integer idCampanha;

    public VacinacaoRequestDTO(Integer id, StatusVacinacaoEnum status, List<Integer> idFuncionarios, Integer idCampanha) {
        super();
        this.id = id;
        this.status = status;
        this.idFuncionarios = idFuncionarios;
        this.idCampanha = idCampanha;
    }
}
