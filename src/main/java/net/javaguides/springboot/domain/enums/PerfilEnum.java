package net.javaguides.springboot.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PerfilEnum {
    ADMIN(0, "ROLE_ADMIN"),
    FUNCIONARIO(1, "ROLE_FUNCIONARIO"),
    TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    public static PerfilEnum toEnum(Integer cod) {
        if (cod == null){
            return null;
        }

        for(PerfilEnum x: PerfilEnum.values()){
           if(cod.equals(x.getCodigo())){
               return x;
           }
        }
        try {
            throw new IllegalAccessException("Perfil inválido");
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
