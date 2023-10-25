package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.ASOResponseDTO;
import net.javaguides.springboot.domain.entity.ASO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ASORepository extends JpaRepository<ASO, Integer> {

    List<ASOResponseDTO> findByPessoaId(Integer idPessoa);
}
