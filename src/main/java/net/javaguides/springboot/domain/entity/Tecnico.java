package net.javaguides.springboot.domain.entity;


import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    public Tecnico(){
        super();
        addPerfil(PerfilEnum.TECNICO);
    }

    public Tecnico(Integer id, String primeiroNome, String ultimoNome, String email, String senha, String setor, String telefone, LocalDate dataAniversario, SexoEnum sexo, String cpf) {
        super(id, primeiroNome, ultimoNome, email, senha, setor, telefone, dataAniversario, sexo, cpf);
        addPerfil(PerfilEnum.TECNICO);
    }

    public Tecnico (TecnicoDTO tecnico){
        super();
        this.id = tecnico.getId();
        this.primeiroNome = tecnico.getPrimeiroNome();
        this.ultimoNome = tecnico.getUltimoNome();
        this.email = tecnico.getEmail();
        if(tecnico.getSenha() != null) this.senha = tecnico.getSenha();
        this.setor = tecnico.getSetor();
        this.telefone = tecnico.getTelefone();
        this.dataAniversario = tecnico.getDataAniversario();
        this.sexoEnum = tecnico.getSexoEnum();
        this.cpf = tecnico.getCpf();
        if(!tecnico.getPerfis().isEmpty()) this.perfis = tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.isApproved = tecnico.getIsApproved();
    }
}
