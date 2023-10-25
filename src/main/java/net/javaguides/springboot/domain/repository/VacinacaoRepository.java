package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Vacinacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacinacaoRepository  extends JpaRepository<Vacinacao, Integer> {
    List<VacinacaoResponseDTO> findByPessoaId(Integer idPessoa);
}
