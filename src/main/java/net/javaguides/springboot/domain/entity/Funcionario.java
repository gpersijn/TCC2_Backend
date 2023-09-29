package net.javaguides.springboot.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Builder
public class Funcionario extends Pessoa {
    private static final long serialVersionUID = 1L;

    public Funcionario(){
        super();
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Funcionario(Integer id, String primeiroNome, String ultimoNome, String email, String senha, String setor, String telefone, LocalDate dataAniversario, SexoEnum sexo, String cpf){
        super(id, primeiroNome, ultimoNome, email, senha, setor, telefone, dataAniversario, sexo, cpf);
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Funcionario (FuncionarioDTO funcionario){
        super();
        this.id = funcionario.getId();
        this.primeiroNome = funcionario.getPrimeiroNome();
        this.ultimoNome = funcionario.getUltimoNome();
        this.email = funcionario.getEmail();
        if(funcionario.getSenha() != null) this.senha = funcionario.getSenha();
        this.setor = funcionario.getSetor();
        this.telefone = funcionario.getTelefone();
        this.dataAniversario = funcionario.getDataAniversario();
        this.sexoEnum = funcionario.getSexoEnum();
        this.cpf = funcionario.getCpf();
        if(!funcionario.getPerfis().isEmpty()) this.perfis = funcionario.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.isApproved = funcionario.getIsApproved();
    }
}
