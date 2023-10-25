package net.javaguides.springboot.domain.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ASOResponseDTO {
    protected Integer idASO;
    protected String cnpj;
    protected String nomeEmpresa;
    protected Pessoa pessoa;
    protected List<String> risco;
    protected List<Exame> exames;
    protected String nomeMedicoPCMSO;
    protected String crmMedicoPCMSO;
    protected String nomeMedicoClinico;
    protected ResultadoASOEnum resultadoASO;
    protected LocalDate validade;

    public ASOResponseDTO(Integer idASO, String cnpj, String nomeEmpresa, Pessoa pessoa, List<String> risco, List<Exame> exames, String nomeMedicoPCMSO, String crmMedicoPCMSO, String nomeMedicoClinico, ResultadoASOEnum resultadoASO, LocalDate validade) {
        super();
        this.idASO = idASO;
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.pessoa = pessoa;
        this.risco = risco;
        this.exames = exames;
        this.nomeMedicoPCMSO = nomeMedicoPCMSO;
        this.crmMedicoPCMSO = crmMedicoPCMSO;
        this.nomeMedicoClinico = nomeMedicoClinico;
        this.resultadoASO = resultadoASO;
        this.validade = validade;
    }

    public ASOResponseDTO(ASO aso) {
        super();
        this.idASO = aso.getIdASO();
        this.cnpj = aso.getCnpj();
        this.nomeEmpresa = aso.getNomeEmpresa();
        this.pessoa = aso.getPessoa();
        this.risco = aso.getRisco();
        this.exames = aso.getExames();
        this.nomeMedicoPCMSO = aso.getNomeMedicoPCMSO();
        this.crmMedicoPCMSO = aso.getCrmMedicoPCMSO();
        this.nomeMedicoClinico = aso.getNomeMedicoClinico();
        this.resultadoASO = aso.getResultadoASO();
        this.validade = aso.getValidade();
    }
}
