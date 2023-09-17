package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexoEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String descricao;
}
