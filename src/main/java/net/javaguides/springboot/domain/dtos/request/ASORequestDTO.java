package net.javaguides.springboot.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ASORequestDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected Integer idASO;

    @NotNull(message = "O CNPJ deve ser preenchido.")
    @NotBlank(message = "O campo CNPJ não pode estar em branco.")
    @CNPJ
    protected String cnpj;

    @NotNull(message = "O campo NOME EMPRESA deve ser preenchido.")
    @NotBlank(message = "O campo NOME EMPRESA não pode estar em branco.")
    protected String nomeEmpresa;

    protected Integer idPessoa;

    protected List<String> risco;

    protected List<Integer> exames;

    @NotNull(message = "O campo NOME MEDICO DO PCMSO deve ser preenchido.")
    @NotBlank(message = "O campo NOME MEDICO DO PCMSO não pode estar em branco.")
    protected String nomeMedicoPCMSO;

    @NotNull(message = "O campo CRM MEDICO DO PCMSO deve ser preenchido.")
    @NotBlank(message = "O campo CRM MEDICO DO PCMSO não pode estar em branco.")
    protected String crmMedicoPCMSO;

    @NotNull(message = "O campo NOME MEDICO CLINICO deve ser preenchido.")
    @NotBlank(message = "O campo NOME MEDICO CLINICO não pode estar em branco.")
    protected String nomeMedicoClinico;

    protected ResultadoASOEnum resultadoASO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate validade;

    public ASORequestDTO(Integer idASO, String cnpj, String nomeEmpresa, Integer idPessoa, List<String> risco, List<Integer> exames, String nomeMedicoPCMSO, String crmMedicoPCMSO, String nomeMedicoClinico, ResultadoASOEnum resultadoASO, LocalDate validade) {
        this.idASO = idASO;
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.idPessoa = idPessoa;
        this.risco = risco;
        this.exames = exames;
        this.nomeMedicoPCMSO = nomeMedicoPCMSO;
        this.crmMedicoPCMSO = crmMedicoPCMSO;
        this.nomeMedicoClinico = nomeMedicoClinico;
        this.resultadoASO = resultadoASO;
        this.validade = validade;
    }
}
