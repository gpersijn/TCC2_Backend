package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.SexoEnum;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listPessoasAprovadas() {
        return pessoaRepository.findByIsApprovedTrue();
    }

    public Integer contarHomens() {
        return pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.MASCULINO, true);
    }

    public Integer contarMulheres() {
        return pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.FEMININO, true);
    }

    public Integer contarOutros() {
        return pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.OUTRO, true);
    }

    public Integer contarPessoas() {
        return pessoaRepository.countByIsApprovedTrue();
    }

    public List<Object[]> contarPessoasPorSetor() {
        return pessoaRepository.countBySetor();
    }
}
