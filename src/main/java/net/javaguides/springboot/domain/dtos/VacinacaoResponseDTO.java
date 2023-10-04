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
    protected Integer idVacinacao;
    protected StatusVacinacaoEnum status;
    protected Pessoa pessoa;
    protected Campanha campanha;

    public VacinacaoResponseDTO(Integer idVacinacao, StatusVacinacaoEnum status, Pessoa pessoa, Campanha campanha) {
        super();
        this.idVacinacao = idVacinacao;
        this.status = status;
        this.pessoa = pessoa;
        this.campanha = campanha;
    }

    public VacinacaoResponseDTO(Vacinacao vacinacao) {
        super();
        this.idVacinacao = vacinacao.getId();
        this.status = vacinacao.getStatus();
        this.pessoa = vacinacao.getPessoa();
        this.campanha = vacinacao.getCampanha();
    }


}
