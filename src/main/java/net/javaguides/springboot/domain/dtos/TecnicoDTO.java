package net.javaguides.springboot.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer idTecnico;

    protected Boolean isApproved;

    @NotNull(message = "O PRIMEIRO NOME deve ser preenchido.")
    @NotBlank(message = "O campo PRIMEIRO NOME não pode estar em branco.")
    protected String primeiroNome;

    @NotNull(message = "O ÚLTIMO NOME deve ser preenchido.")
    @NotBlank(message = "O campo ÚLTIMO NOME não pode estar em branco.")
    protected String ultimoNome;

    @NotNull(message = "O campo EMAIL deve ser preenchido.")
    @NotBlank(message = "O campo EMAIL não pode estar em branco.")
    @Email
    protected String email;

    protected String senha;

    @NotNull(message = "O campo SETOR deve ser preenchido.")
    @NotBlank(message = "O campo SETOR não pode estar em branco.")
    protected String setor;

    @NotNull(message = "O campo CARGO deve ser preenchido.")
    @NotBlank(message = "O campo CARGO não pode estar em branco.")
    protected String cargo;

    protected String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "O campo DATA ANIVERSARIO deve ser preenchido.")
    protected LocalDate dataAniversario;

    @NotNull(message = "O campo SEXO deve ser preenchido.")
    protected SexoEnum sexoEnum;

    @NotNull(message = "O campo CPF deve ser preenchido.")
    @NotBlank(message = "O campo CPF não pode estar em branco.")
    @CPF(message = "O campo CPF deve ser válido")
    protected String cpf;

    protected Set<Integer> perfis = new HashSet<>();

    public TecnicoDTO(Tecnico tecnico){
        super();
        this.idTecnico = tecnico.getId();
        this.primeiroNome = tecnico.getPrimeiroNome();
        this.ultimoNome = tecnico.getUltimoNome();
        this.email = tecnico.getEmail();
        this.senha = tecnico.getSenha();
        this.setor = tecnico.getSetor();
        this.cargo= tecnico.getCargo();
        this.telefone = tecnico.getTelefone();
        this.dataAniversario = tecnico.getDataAniversario();
        this.sexoEnum = tecnico.getSexoEnum();
        this.cpf = tecnico.getCpf();
        this.perfis = tecnico.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.isApproved = tecnico.getIsApproved();
    }

    public TecnicoDTO(){
        super();
        addPerfil(PerfilEnum.TECNICO);
    }

    public Set<PerfilEnum> getPerfis(){
        return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(PerfilEnum perfilEnum){
        this.perfis.add(perfilEnum.getCodigo());
    }

}
