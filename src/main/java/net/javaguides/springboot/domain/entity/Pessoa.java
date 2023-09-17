package net.javaguides.springboot.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "pessoa")
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected Boolean isApproved;

    protected String primeiroNome;

    protected String ultimoNome;

    @Column(unique = true)
    protected String email;

    protected String senha;

    protected String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataAniversario;

    protected SexoEnum sexoEnum;

    @CPF
    @Column(unique = true)
    protected String cpf;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>();

    public Pessoa(){
        super();
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Pessoa(Integer id, String primeiroNome, String ultimoNome, String email, String senha, String telefone, LocalDate dataAniversario, SexoEnum sexoEnum, String cpf) {
        super();
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.dataAniversario = dataAniversario;
        this.sexoEnum = sexoEnum;
        this.cpf = cpf;
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Set<PerfilEnum> getPerfis(){
        return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(PerfilEnum perfilEnum){
        this.perfis.add(perfilEnum.getCodigo());

    }
}
