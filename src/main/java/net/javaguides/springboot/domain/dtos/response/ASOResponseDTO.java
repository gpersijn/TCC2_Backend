package net.javaguides.springboot.domain.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;
import net.javaguides.springboot.domain.enums.TipoASOEnum;

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
    protected TipoASOEnum tipoASO;
    protected String crmMedicoPCMSO;
    protected String nomeMedicoClinico;
    protected String crmMedicoClinico;
    protected ResultadoASOEnum resultadoASO;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate validade;
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataASO;

    public ASOResponseDTO(Integer idASO, String cnpj, String nomeEmpresa, Pessoa pessoa, List<String> risco, List<Exame> exames, String nomeMedicoPCMSO, String crmMedicoClinico, TipoASOEnum tipoASO, String crmMedicoPCMSO, String nomeMedicoClinico, ResultadoASOEnum resultadoASO, LocalDate validade, LocalDate dataASO) {
        super();
        this.idASO = idASO;
        this.cnpj = cnpj;
        this.nomeEmpresa = nomeEmpresa;
        this.pessoa = pessoa;
        this.risco = risco;
        this.exames = exames;
        this.nomeMedicoPCMSO = nomeMedicoPCMSO;
        this.tipoASO = tipoASO;
        this.crmMedicoPCMSO = crmMedicoPCMSO;
        this.nomeMedicoClinico = nomeMedicoClinico;
        this.crmMedicoClinico = crmMedicoClinico;
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
        this.tipoASO = aso.getTipoASO();
        this.crmMedicoPCMSO = aso.getCrmMedicoPCMSO();
        this.nomeMedicoClinico = aso.getNomeMedicoClinico();
        this.crmMedicoClinico = aso.getCrmMedicoClinico();
        this.resultadoASO = aso.getResultadoASO();
        this.validade = aso.getValidade();
        this.dataASO = aso.getDataASO();
    }
}
