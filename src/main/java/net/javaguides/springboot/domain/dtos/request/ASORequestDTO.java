package net.javaguides.springboot.domain.dtos.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;
import net.javaguides.springboot.domain.enums.TipoASOEnum;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    protected TipoASOEnum tipoASO;

    @NotNull(message = "O campo CRM MEDICO DO PCMSO deve ser preenchido.")
    @NotBlank(message = "O campo CRM MEDICO DO PCMSO não pode estar em branco.")
    protected String crmMedicoPCMSO;

    @NotNull(message = "O campo NOME MEDICO CLINICO deve ser preenchido.")
    @NotBlank(message = "O campo NOME MEDICO CLINICO não pode estar em branco.")
    protected String nomeMedicoClinico;

    @NotNull(message = "O campo CRM MEDICO CLINICO deve ser preenchido.")
    @NotBlank(message = "O campo CRM MEDICO CLINICO não pode estar em branco.")
    @Pattern(regexp = "^(.*[A-Z]){2,}.*(\\d).*$", message = "CRM inválido, deve conter pelo menos 2 letras e 1 número.")
    protected String crmMedicoClinico;

    protected ResultadoASOEnum resultadoASO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate validade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataASO;

    public ASORequestDTO(Integer idASO, String cnpj, String nomeEmpresa, Integer idPessoa, List<String> risco, List<Integer> exames, String nomeMedicoPCMSO, TipoASOEnum tipoASO, String crmMedicoPCMSO, String nomeMedicoClinico, String crmMedicoClinico, ResultadoASOEnum resultadoASO, LocalDate validade, LocalDate dataASO) {
        this.idASO = idASO;
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.idPessoa = idPessoa;
        this.risco = risco;
        this.exames = exames;
        this.nomeMedicoPCMSO = nomeMedicoPCMSO;
        this.tipoASO = tipoASO;
        this.crmMedicoPCMSO = crmMedicoPCMSO;
        this.nomeMedicoClinico = nomeMedicoClinico;
        this.crmMedicoClinico = crmMedicoClinico;
        this.resultadoASO = resultadoASO;
        this.validade = validade;
        this.dataASO = dataASO;
    }
}
