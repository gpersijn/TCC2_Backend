package net.javaguides.springboot.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Funcionario extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Chamado> chamados = new ArrayList<>();

    public Funcionario(){
        super();
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Funcionario(Integer id, String primeiroNome, String ultimoNome, String email, String senha, String telefone, LocalDate dataAniversario, SexoEnum sexo, String cpf){
        super(id, primeiroNome, ultimoNome, email, senha, telefone, dataAniversario, sexo, cpf);
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Funcionario (FuncionarioDTO funcionario){
        super();
        this.id = funcionario.getId();
        this.primeiroNome = funcionario.getPrimeiroNome();
        this.ultimoNome = funcionario.getUltimoNome();
        this.email = funcionario.getEmail();
        this.senha = funcionario.getSenha();
        this.telefone = funcionario.getTelefone();
        this.dataAniversario = funcionario.getDataAniversario();
        this.sexoEnum = funcionario.getSexoEnum();
        this.cpf = funcionario.getCpf();
        this.perfis = funcionario.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.isApproved = funcionario.getIsApproved();
    }
}
