package net.javaguides.springboot.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.ExameRequestDTO;
import net.javaguides.springboot.domain.enums.StatusExameEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Builder
public class Exame implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idExame;

    protected String nomeExame;

    protected StatusExameEnum statusExame;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataExame;

    @JsonFormat(pattern = "HH:mm")
    protected LocalTime horaExame;

    protected String localExame;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    protected Pessoa pessoa;

    public Exame(ExameRequestDTO dto, Pessoa pessoa){
        super();
        this.idExame = dto.getIdExame();
        this.nomeExame = dto.getNomeExame();
        this.statusExame = dto.getStatusExame();
        this.dataExame = dto.getDataExame();
        this.horaExame = dto.getHoraExame();
        this.localExame = dto.getLocalExame();
        this.pessoa = pessoa;
    }

    public Exame(Integer id, String localExame, StatusExameEnum statusExame, LocalDate dataExame, LocalTime horaExame, String nomeExame, Pessoa pessoa) {
        super();
        this.idExame = id;
        this.localExame = localExame;
        this.statusExame = statusExame;
        this.dataExame = dataExame;
        this.horaExame = horaExame;
        this.nomeExame = nomeExame;
        this.pessoa = pessoa;
    }

    public Exame(){
    }

}
