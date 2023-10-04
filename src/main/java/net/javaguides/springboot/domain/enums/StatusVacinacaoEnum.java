package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusVacinacaoEnum {
    PENDENTE,
    CONCLUÍDO,
    ATRASADO,
    CANCELADO
}
