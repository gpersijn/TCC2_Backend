package net.javaguides.springboot.domain.dtos;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.PerfilEnum;

@Getter
@Setter
public class PerfilDTO {

    private String email;
    private PerfilEnum perfilEnum;
}
