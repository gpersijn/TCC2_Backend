package net.javaguides.springboot.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import net.javaguides.springboot.domain.enums.PrioridadeEnum;
import net.javaguides.springboot.domain.enums.StatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@Setter
@Entity
@Table(name = "chamado")
public class Chamado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    private PrioridadeEnum prioridadeEnum;

    private StatusEnum statusEnum;

    private String titulo;

    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Chamado(Integer id, PrioridadeEnum prioridadeEnum, StatusEnum statusEnum, String titulo, String observacoes, Tecnico tecnico,
                   Funcionario funcionario) {
        super();
        this.id = id;
        this.prioridadeEnum = prioridadeEnum;
        this.statusEnum = statusEnum;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.funcionario = funcionario;
    }

}
