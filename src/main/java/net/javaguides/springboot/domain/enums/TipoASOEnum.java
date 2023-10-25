package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoASOEnum {
    ADMISSIONAL,
    PEIRODICO,
    RETORNO,
    MUDANCA,
    DEMISSIONAL
}
