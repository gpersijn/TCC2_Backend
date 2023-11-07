package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExameRepository extends JpaRepository<Exame, Integer> {

    List<ExameResponseDTO> findByPessoaId(Integer idPessoa);
    @Query("SELECT v.tipoExame, COUNT(v) FROM Exame v GROUP BY v.tipoExame")
    List<Object[]> listQuantidadeTiposExame();
    @Query("SELECT v.statusExame, COUNT(v) FROM Exame v GROUP BY v.statusExame")
    List<Object[]> listQuantidadeStatusExame();
}
