package net.javaguides.springboot.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.enums.ResultadoASOEnum;
import net.javaguides.springboot.domain.enums.TipoASOEnum;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
public class ASO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer idASO;

    @CNPJ
    @Column(unique = true)
    protected String cnpj;

    protected String nomeEmpresa;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    protected Pessoa pessoa;

    @ElementCollection
    protected List<String> risco;

    @ManyToMany
    @JoinTable(name = "aso_exame",
            joinColumns = @JoinColumn(name = "idASO"),
            inverseJoinColumns = @JoinColumn(name = "idExame"))
    protected List<Exame> exames;

    protected String nomeMedicoPCMSO;

    protected TipoASOEnum tipoASO;

    protected String crmMedicoPCMSO;

    protected String nomeMedicoClinico;

    protected ResultadoASOEnum resultadoASO;

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate validade;

    public ASO(Integer idASO, String cnpj, String nomeEmpresa, Pessoa pessoa, List<String> risco, List<Exame> exames, String nomeMedicoPCMSO, TipoASOEnum tipoASO, String crmMedicoPCMSO, String nomeMedicoClinico, ResultadoASOEnum resultadoASO, LocalDate validade) {
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
        this.resultadoASO = resultadoASO;
        this.validade = validade;
    }

    public ASO(ASORequestDTO dto, List<Exame> listaExames, Pessoa pessoa) {
        super();
        this.idASO = dto.getIdASO();
        this.cnpj = dto.getCnpj();
        this.nomeEmpresa = dto.getNomeEmpresa();
        this.pessoa = pessoa;
        this.risco = dto.getRisco();
        this.exames = listaExames;
        this.nomeMedicoPCMSO = dto.getNomeMedicoPCMSO();
        this.tipoASO = dto.getTipoASO();
        this.crmMedicoPCMSO = dto.getCrmMedicoPCMSO();
        this.nomeMedicoClinico = dto.getNomeMedicoClinico();
        this.resultadoASO = dto.getResultadoASO();
        this.validade = dto.getValidade();
    }

    public ASO() {
    }
}
