package net.javaguides.springboot.domain.repository;

import net.javaguides.springboot.domain.entity.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampanhaRepository extends JpaRepository<Campanha, Integer> {
}
