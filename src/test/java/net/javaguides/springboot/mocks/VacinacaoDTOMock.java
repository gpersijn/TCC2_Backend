package net.javaguides.springboot.mocks;

import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestAtualizacaoDTO;
import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestDTO;
import net.javaguides.springboot.domain.dtos.response.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

import java.util.List;

public class VacinacaoDTOMock {

    public static VacinacaoResponseDTO withResponseValues() {
        Vacinacao vacinacao = new Vacinacao();
        vacinacao.setId(1);
        vacinacao.setStatus(StatusVacinacaoEnum.CONCLUIDO);
        vacinacao.setPessoa(new Funcionario());
        vacinacao.setCampanha(new Campanha());

        return new VacinacaoResponseDTO(vacinacao);
    }

    public static VacinacaoRequestDTO withRequestValues() {
        return new VacinacaoRequestDTO(1, StatusVacinacaoEnum.CONCLUIDO, List.of(1), 1);
    }

    public static VacinacaoRequestAtualizacaoDTO withUpdateValues() {
        VacinacaoRequestAtualizacaoDTO dto = new VacinacaoRequestAtualizacaoDTO();
        dto.setStatusVacinacao(StatusVacinacaoEnum.CONCLUIDO);
        return dto;
    }
}
