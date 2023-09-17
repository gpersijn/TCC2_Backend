package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    ABERTO(0, "ABERTO"),
    ANDAMENTO(1, "ANDAMENTO"),
    ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    public static Object toEnum(Integer cod) throws IllegalAccessException {
        if (cod == null){
            return null;
        }

        for(StatusEnum x: StatusEnum.values()){
           if(cod.equals(x.getCodigo())){
               return x;
           }
        }
        throw new IllegalAccessException("Status inválido");
    }
}
