package net.javaguides.springboot.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.CampanhaDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
public class Campanha implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nomeCampanha;

    protected String nomeVacina;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCampanha;

    protected String descricao;

    public Campanha(CampanhaDTO campanha) {
        super();
        this.id = campanha.getId();
        this.nomeCampanha = campanha.getNomeCampanha();
        this.nomeVacina = campanha.getNomeVacina();
        this.dataCampanha = campanha.getDataCampanha();
        this.descricao = campanha.getDescricao();
    }

    public Campanha(Integer id, String nomeCampanha, String nomeVacina, LocalDate dataCampanha, String descricao){
        super();
    }

    public Campanha() {
    }
}
