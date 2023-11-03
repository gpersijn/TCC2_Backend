package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.SexoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByCpf(String cpf);
    Optional<Pessoa> findByEmail(String email);
    List<Pessoa> findByIsApprovedTrue();
    Integer countBySexoEnumAndIsApproved(SexoEnum sexoEnum, Boolean isApproved);
    @Query("SELECT p.setor, COUNT(p) FROM Pessoa p WHERE p.isApproved = true GROUP BY p.setor")
    List<Object[]> countBySetor();
    Integer countByIsApprovedTrue();
}
