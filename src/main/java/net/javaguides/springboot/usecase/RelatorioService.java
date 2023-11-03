package net.javaguides.springboot.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ASOService asoService;

    public Map<String, Object> getDadosSexo() {
        Map<String, Object> response = new HashMap<>();
        Integer qtdHomens = pessoaService.contarHomens();
        Integer qtdMulheres = pessoaService.contarMulheres();
        Integer totalPessoas = pessoaService.contarPessoas();

        response.put("total pessoas", Long.valueOf(totalPessoas));

        Map<String, Integer> dadosSexo = new HashMap<>();
        dadosSexo.put("homens", qtdHomens);
        dadosSexo.put("mulheres", qtdMulheres);
        response.put("dados", dadosSexo);

        Map<String, Double> dadosPorcentagem = new HashMap<>();
        dadosPorcentagem.put("homens", Math.round(((double) qtdHomens / totalPessoas) * 1000) / 10.0);
        dadosPorcentagem.put("mulheres", Math.round(((double) qtdMulheres / totalPessoas) * 1000) / 10.0);
        response.put("porcentagem", dadosPorcentagem);

        return response;
    }

    public Map<String, Object> getDadosSetor() {
        Map<String, Object> response = new HashMap<>();
        List<Object[]> resultado = pessoaService.contarPessoasPorSetor();

        int qtdNaoNula = 0;
        long totalPessoas = resultado.stream().mapToLong(row -> (Long) row[1]).sum();

        Map<String, Integer> dadosSetor = new HashMap<>();
        Map<String, Double> dadosPorcentagem = new HashMap<>();
        for (Object[] row : resultado) {
            String setor = (String) row[0];
            if (setor != null) {
                Long quantidade = (Long) row[1];
                dadosSetor.put(setor, quantidade.intValue());
                dadosPorcentagem.put(setor, Math.round((double) quantidade / totalPessoas * 1000) / 10.0);
                qtdNaoNula+=quantidade;
            }
        }

        int qtdSemSetor = (int) (totalPessoas-qtdNaoNula);
        if(qtdSemSetor > 0) {
            dadosSetor.put("Sem setor", qtdSemSetor);
            dadosPorcentagem.put("Sem setor", Math.round((double) qtdSemSetor / totalPessoas * 1000) / 10.0);
        }

        response.put("total pessoas", Long.valueOf(totalPessoas));
        response.put("dados", dadosSetor);
        response.put("porcentagem", dadosPorcentagem);

        return response;
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
        response.put("total pessoas", total);

        return response;
    }

}
