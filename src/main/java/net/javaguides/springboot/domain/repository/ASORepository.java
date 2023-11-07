package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.dtos.response.ASOResponseDTO;
import net.javaguides.springboot.domain.entity.ASO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ASORepository extends JpaRepository<ASO, Integer> {

    List<ASOResponseDTO> findByPessoaId(Integer idPessoa);

    @Query("SELECT COUNT(DISTINCT a.pessoa) FROM ASO a " +
            "WHERE a.idASO IN " +
            "(SELECT MAX(b.idASO) FROM ASO b WHERE b.pessoa = a.pessoa GROUP BY b.pessoa) " +
            "AND a.validade > CURRENT_DATE " +
            "AND a.resultadoASO = 0")
    Long countAptoASOs();

    @Query("SELECT COUNT(DISTINCT a.pessoa) FROM ASO a " +
            "WHERE a.idASO IN " +
            "(SELECT MAX(b.idASO) FROM ASO b WHERE b.pessoa = a.pessoa GROUP BY b.pessoa) " +
            "AND a.validade > CURRENT_DATE " +
            "AND a.resultadoASO = 1")
    Long countInaptoASOs();

}
