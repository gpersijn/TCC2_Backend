package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByEmail(String email);
    List<Funcionario> findByIsApprovedFalse();
}
