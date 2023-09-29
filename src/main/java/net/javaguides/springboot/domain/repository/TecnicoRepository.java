package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    Optional<Tecnico> findByEmail(String email);
    List<Tecnico> findByIsApprovedFalse();
}
