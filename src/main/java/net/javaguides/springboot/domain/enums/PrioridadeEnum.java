package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PrioridadeEnum {
    BAIXA(0, "BAIXA"),
    MEDIA(1, "MEDIA"),
    ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    public static PrioridadeEnum toEnum(Integer cod) {
        if (cod == null){
            return null;
        }

        for(PrioridadeEnum x: PrioridadeEnum.values()){
           if(cod.equals(x.getCodigo())){
               return x;
           }
        }
        try {
            throw new IllegalAccessException("Prioridade inválido");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
