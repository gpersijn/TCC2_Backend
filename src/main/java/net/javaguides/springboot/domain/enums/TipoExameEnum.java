package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoExameEnum {
    ADMISSIONAL,
    PEIRODICO,
    RETORNO,
    MUDANCA,
    DEMISSIONAL
}
