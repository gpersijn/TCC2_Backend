package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacinacaoRepository  extends JpaRepository<Vacinacao, Integer> {
    List<VacinacaoResponseDTO> findByPessoaId(Integer idPessoa);
    @Query("SELECT v.campanha.nomeCampanha, " +
            "SUM(CASE WHEN v.status = 'CONCLUÍDO' OR v.status = 'PENDENTE' OR v.status = 'ATRASADO' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN v.status = 'CONCLUÍDO' THEN 1 ELSE 0 END) " +
            "FROM Vacinacao v " +
            "GROUP BY v.campanha.nomeCampanha")
    List<Object[]> listCountVacinacoesPorCampanha();
    @Query("SELECT COUNT(v) FROM Vacinacao v WHERE v.campanha.id = :idCampanha AND v.status = :status")
    Integer contarQuantidadePorStatusCampanha(@Param("idCampanha") Integer idCampanha, @Param("status") StatusVacinacaoEnum status);

}
