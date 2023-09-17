package net.javaguides.springboot.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
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
public class Tecnico extends Pessoa {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(){
        super();
        addPerfil(PerfilEnum.TECNICO);
    }

    public Tecnico(Integer id, String primeiroNome, String ultimoNome, String email, String senha, String telefone, LocalDate dataAniversario, SexoEnum sexo, String cpf) {
        super(id, primeiroNome, ultimoNome, email, senha, telefone, dataAniversario, sexo, cpf);
        addPerfil(PerfilEnum.TECNICO);
    }

    public Tecnico (TecnicoDTO tecnico){
        super();
        this.id = tecnico.getId();
        this.primeiroNome = tecnico.getPrimeiroNome();
        this.ultimoNome = tecnico.getUltimoNome();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.telefone = tecnico.getTelefone();
        this.dataAniversario = tecnico.getDataAniversario();
        this.sexoEnum = tecnico.getSexoEnum();
        this.cpf = tecnico.getCpf();
        this.perfis = tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.isApproved = tecnico.getIsApproved();
    }
}
