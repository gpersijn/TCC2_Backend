package net.javaguides.springboot.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class VacinacaoAtualizacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private StatusVacinacaoEnum statusVacinacao;

    public VacinacaoAtualizacaoDTO(final StatusVacinacaoEnum statusVacinacao) {
        this.statusVacinacao = statusVacinacao;
    }
}
