package net.javaguides.springboot.domain.entity;

import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Vacinacao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Enumerated(EnumType.STRING)
    protected StatusVacinacaoEnum status;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    protected Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idCampanha")
    protected Campanha campanha;

    public Vacinacao() {
    }

    public Vacinacao(Integer id, StatusVacinacaoEnum status, Campanha idCampanha, Pessoa pessoa) {
        super();
        this.id = id;
        this.status = status;
        this.campanha = idCampanha;
        this.pessoa = pessoa;
    }


}
