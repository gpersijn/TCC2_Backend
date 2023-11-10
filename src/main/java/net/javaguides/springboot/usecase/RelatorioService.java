package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ASOService asoService;

    @Autowired
    private ExameService exameService;

    @Autowired
    private VacinacaoService vacinacaoService;

    public Map<String, Object> getDadosSexo() {
        Map<String, Object> response = new HashMap<>();
        Integer qtdHomens = pessoaService.contarHomens();
        Integer qtdMulheres = pessoaService.contarMulheres();
        Integer qtdOutros = pessoaService.contarOutros();
        Integer totalPessoas = pessoaService.contarPessoas();

        response.put("total pessoas", Long.valueOf(totalPessoas));

        Map<String, Integer> dadosSexo = new HashMap<>();
        dadosSexo.put("homens", qtdHomens);
        dadosSexo.put("mulheres", qtdMulheres);
        dadosSexo.put("outros", qtdOutros);
        response.put("dados", dadosSexo);

        Map<String, Double> dadosPorcentagem = new HashMap<>();
        dadosPorcentagem.put("homens", Math.round(((double) qtdHomens / totalPessoas) * 1000) / 10.0);
        dadosPorcentagem.put("mulheres", Math.round(((double) qtdMulheres / totalPessoas) * 1000) / 10.0);
        dadosPorcentagem.put("outros", Math.round(((double) qtdOutros / totalPessoas) * 1000) / 10.0);
        response.put("porcentagem", dadosPorcentagem);

        return response;
    }

    public Object[] getDadosSetor() {
        List<Object[]> resultado = pessoaService.contarPessoasPorSetor();
        int qtdNaoNula = 0;
        long totalPessoas = resultado.stream().mapToLong(row -> (Long) row[1]).sum();

        List<Object> responseList = new ArrayList<>();

        for (Object[] row : resultado) {
            String setor = (String) row[0];
            if (setor != null) {
                Long quantidade = (Long) row[1];
                Map<String, Object> setorData = new HashMap<>();
                setorData.put("setor", setor);
                setorData.put("quantidade", quantidade.intValue());
                setorData.put("porcentagem", Math.round((double) quantidade / totalPessoas * 1000) / 10.0);
                responseList.add(setorData);
                qtdNaoNula += quantidade;
            }
        }

        int qtdSemSetor = (int) (totalPessoas - qtdNaoNula);
        if (qtdSemSetor > 0) {
            Map<String, Object> semSetorData = new HashMap<>();
            semSetorData.put("setor", "Sem setor");
            semSetorData.put("quantidade", qtdSemSetor);
            semSetorData.put("porcentagem", qtdSemSetor);
            responseList.add(semSetorData);
        }

        return responseList.toArray();
    }

    public Map<String, Object> getDadosAptidao() {
        Map<String, Object> response = new HashMap<>();

        long qtdAPTO = asoService.countAptoASOs();
        long qtdINAPTO = asoService.countInaptoASOs();
        long total = qtdAPTO+qtdINAPTO;

        Map<String, Long> dadosApto = new HashMap<>();
        dadosApto.put("apto", qtdAPTO);
        dadosApto.put("inapto", qtdINAPTO);

        Map<String, Double> dadosPorcentagem = new HashMap<>();
        dadosPorcentagem.put("apto", Math.round((double) qtdAPTO / total * 1000) / 10.0);
        dadosPorcentagem.put("inapto", Math.round((double) qtdINAPTO / total * 1000) / 10.0);

        response.put("dados", dadosApto);
        response.put("porcentagem", dadosPorcentagem);
        response.put("total pessoas com ASO", total);

        return response;
    }

    public Object[] getDadosCampanha() {
        List<Object> responseList = new ArrayList<>();
        List<Object[]> listaCampanhaContagem = vacinacaoService.listContagemVacinacoesPorCampanha();

        for (Object[] row : listaCampanhaContagem) {
            String campanha = (String) row[0];
            Long qtdVacinacoes = (Long) row[1];
            Long qtdVacinados = (Long) row[2];

            Map<String, Object> campanhaData = new HashMap<>();
            campanhaData.put("campanha", campanha);
            campanhaData.put("quantidadeVacinacoes", qtdVacinacoes);
            campanhaData.put("quantidadeVacinados", qtdVacinados);

            responseList.add(campanhaData);
        }
        return responseList.toArray();
    }

    public Object[] getDadosStatusCampanha(Integer idCampanha) {
        List<Object> responseList = new ArrayList<>();
        Integer totalPorCampanha = vacinacaoService.contarQuantidadeTotalPorCampanha(idCampanha);

        for (StatusVacinacaoEnum statusEnum : StatusVacinacaoEnum.values()) {
            Integer quantidade = vacinacaoService.contarQuantidadePorStatusCampanha(idCampanha, statusEnum);
            String status = statusEnum.toString();

            Map<String, Object> dadosStatus = new HashMap<>();
            dadosStatus.put("status", status);
            dadosStatus.put("quantidade", quantidade);
            dadosStatus.put("porcentagem", Math.round((double) quantidade / totalPorCampanha * 1000) / 10.0);
            responseList.add(dadosStatus);
        }

        return responseList.toArray();
    }

    public Object[] getDadosExames() {
        List<Object> responseList = new ArrayList<>();
        List<Object[]> lista = exameService.conteQuantidadePorTipoExame();
        Month mesAtual = Month.of(LocalDate.now().getMonthValue());
        Month mesLimite = mesAtual.minus(6);

        for(Object[] row : lista){
            TipoExameEnum tipoExame = (TipoExameEnum) row[0];
            Long quantidade = (Long) row[1];
            Integer mes = (Integer) row[2];

            if (Month.of(mes).compareTo(mesLimite) >= 0 && Month.of(mes).compareTo(mesAtual) <= 0) {
                Map<String, Object> dadosExame = new HashMap<>();
                dadosExame.put("tipoExame", tipoExame.toString());
                dadosExame.put("quantidade", quantidade);
                dadosExame.put("mes", mes);
                responseList.add(dadosExame);
            }

        }

        return responseList.toArray();
    }

    public Object[] getDadosStatusExames() {
        List<Object> responseList = new ArrayList<>();
        List<Object[]> lista = exameService.conteQuantidadePorStatusExame();

        for(Object[] row : lista){
            StatusExameEnum statusExame = (StatusExameEnum) row[0];
            Long quantidade = (Long) row[1];

            Map<String, Object> dadosExame = new HashMap<>();
            dadosExame.put("statusExame", statusExame.toString());
            dadosExame.put("quantidade", quantidade);
            responseList.add(dadosExame);
        }

        return responseList.toArray();
    }
}
