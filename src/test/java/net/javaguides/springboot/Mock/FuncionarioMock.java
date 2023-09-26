package net.javaguides.springboot.Mock;


import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class FuncionarioMock {

    public static FuncionarioDTO valorPadraoDto() {
        Set<Integer> perfis = new HashSet<>();
        perfis.add(PerfilEnum.FUNCIONARIO.getCodigo());

        return FuncionarioDTO.builder()
                .id(1)
                .isApproved(true)
                .primeiroNome("John")
                .ultimoNome("Doe")
                .email("johndoe@example.com")
                .senha("senha123")
                .setor("RH")
                .telefone("1234567890")
                .dataAniversario(LocalDate.of(1990, 5, 15))
                .sexoEnum(SexoEnum.MASCULINO)
                .cpf("12345678901")
                .perfis(perfis)
                .build();

    }


}
