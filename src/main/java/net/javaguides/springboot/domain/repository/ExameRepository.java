package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Integer> {

    List<ExameResponseDTO> findByPessoaId(Integer idPessoa);
}
