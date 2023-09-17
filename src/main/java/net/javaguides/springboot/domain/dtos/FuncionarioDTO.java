package net.javaguides.springboot.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class FuncionarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer id;

    protected Boolean isApproved;

    @NotNull(message = "O PRIMEIRO NOME deve ser preenchido.")
    @NotBlank(message = "O campo PRIMEIRO NOME não pode estar em branco.")
    protected String primeiroNome;

    @NotNull(message = "O ÚLTIMO NOME deve ser preenchido.")
    @NotBlank(message = "O campo ÚLTIMO NOME não pode estar em branco.")
    protected String ultimoNome;

    @NotNull(message = "O campo EMAIL deve ser preenchido.")
    @NotBlank(message = "O campo EMAIL não pode estar em branco.")
    protected String email;

    @NotNull(message = "O campo SENHA deve ser preenchido.")
    @NotBlank(message = "O campo SENHA não pode estar em branco.")
    protected String senha;

    protected String telefone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "O campo DATA ANIVERSARIO deve ser preenchido.")
    protected LocalDate dataAniversario;

    @NotNull(message = "O campo SEXO deve ser preenchido.")
    protected SexoEnum sexoEnum;

    @NotNull(message = "O campo CPF deve ser preenchido.")
    @NotBlank(message = "O campo CPF não pode estar em branco.")
    protected String cpf;

    protected Set<Integer> perfis = new HashSet<>();

    public FuncionarioDTO(Funcionario funcionario){
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

    public FuncionarioDTO(){
        super();
        addPerfil(PerfilEnum.FUNCIONARIO);
    }

    public Set<PerfilEnum> getPerfis(){
        return perfis.stream().map(x -> PerfilEnum.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(PerfilEnum perfilEnum){
        this.perfis.add(perfilEnum.getCodigo());
    }

}
