package net.javaguides.springboot.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

@Getter
@Setter
@Builder
public class VacinacaoResponseDTO {
    protected Integer id;
    protected StatusVacinacaoEnum status;
    protected Pessoa pessoa;
    protected Campanha campanha;

    public VacinacaoResponseDTO(Integer id, StatusVacinacaoEnum status, Pessoa pessoa, Campanha campanha) {
        super();
        this.id = id;
        this.status = status;
        this.pessoa = pessoa;
        this.campanha = campanha;
    }

    public VacinacaoResponseDTO(Vacinacao vacinacao) {
        super();
        this.id = vacinacao.getId();
        this.status = vacinacao.getStatus();
        this.pessoa = vacinacao.getPessoa();
        this.campanha = vacinacao.getCampanha();
    }


}
