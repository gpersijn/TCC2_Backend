package net.javaguides.springboot.mocks;

import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FuncionarioDTOMock {

    public static FuncionarioDTO withDefaultValues() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setId(1);
        dto.setIsApproved(Boolean.FALSE);
        dto.setSenha("hashedPassword");
        dto.setPrimeiroNome("John");
        dto.setUltimoNome("Doe");
        dto.setEmail("johndoe@example.com");
        dto.setSetor("IT");
        dto.setTelefone("1234567890");
        dto.setDataAniversario(LocalDate.of(1990, 1, 1));
        dto.setSexoEnum(SexoEnum.MASCULINO);
        dto.setCpf("12345678901");
        Set<Integer> perfis = new HashSet<>();
        perfis.add(PerfilEnum.TECNICO.getCodigo());
        dto.setPerfis(perfis);
        return dto;
    }
}
