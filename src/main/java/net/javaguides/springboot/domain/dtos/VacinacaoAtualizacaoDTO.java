package net.javaguides.springboot.domain.dtos;

import lombok.*;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacinacaoAtualizacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private StatusVacinacaoEnum statusVacinacao;

}
