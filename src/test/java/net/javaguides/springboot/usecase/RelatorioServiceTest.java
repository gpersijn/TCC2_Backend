package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RelatorioServiceTest {

    @Mock
    private PessoaService pessoaService;

    @Mock
    private ASOService asoService;

    @Mock
    private ExameService exameService;

    @Mock
    private VacinacaoService vacinacaoService;

    @InjectMocks
    private RelatorioService relatorioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetDadosSexo() {
        // Mocking
        when(pessoaService.contarHomens()).thenReturn(50);
        when(pessoaService.contarMulheres()).thenReturn(30);
        when(pessoaService.contarOutros()).thenReturn(10);
        when(pessoaService.contarPessoas()).thenReturn(90);

        // Test
        Map<String, Object> result = relatorioService.getDadosSexo();

        // Assertion
        assertEquals(90L, result.get("total pessoas"));
        assertEquals(50, ((Map<String, Integer>) result.get("dados")).get("homens"));
        assertEquals(30, ((Map<String, Integer>) result.get("dados")).get("mulheres"));
        assertEquals(10, ((Map<String, Integer>) result.get("dados")).get("outros"));
        assertEquals(55.6, ((Map<String, Double>) result.get("porcentagem")).get("homens"));
        assertEquals(33.3, ((Map<String, Double>) result.get("porcentagem")).get("mulheres"));
        assertEquals(11.1, ((Map<String, Double>) result.get("porcentagem")).get("outros"));
    }

    @Test
    void testGetDadosSetor() {
        // Mocking
        when(pessoaService.contarPessoasPorSetor()).thenReturn(List.of(new Object[]{"IT", 30L}, new Object[]{"HR", 20L}));

        // Test
        Object[] result = relatorioService.getDadosSetor();

        // Assertion
        assertEquals(2, result.length);
        assertEquals("IT", ((Map<String, Object>) result[0]).get("setor"));
        assertEquals(30, ((Map<String, Object>) result[0]).get("quantidade"));
        assertEquals(60.0, ((Map<String, Object>) result[0]).get("porcentagem"));
        assertEquals("HR", ((Map<String, Object>) result[1]).get("setor"));
        assertEquals(20, ((Map<String, Object>) result[1]).get("quantidade"));
        assertEquals(40.0, ((Map<String, Object>) result[1]).get("porcentagem"));
    }

    @Test
    void testGetDadosAptidao() {
        // Mocking
        when(asoService.countAptoASOs()).thenReturn(70L);
        when(asoService.countInaptoASOs()).thenReturn(30L);

        // Test
        Map<String, Object> result = relatorioService.getDadosAptidao();

        // Assertion
        assertEquals(100L, result.get("total pessoas com ASO"));
        assertEquals(70, ((Map<String, Long>) result.get("dados")).get("apto"));
        assertEquals(30, ((Map<String, Long>) result.get("dados")).get("inapto"));
        assertEquals(70.0, ((Map<String, Double>) result.get("porcentagem")).get("apto"));
        assertEquals(30.0, ((Map<String, Double>) result.get("porcentagem")).get("inapto"));
    }

    @Test
    void testGetDadosCampanha() {
        // Mocking
        when(vacinacaoService.listContagemVacinacoesPorCampanha()).thenReturn(
                List.of(new Object[]{"Flu Vaccination", 100L, 80L}, new Object[]{"Covid Vaccination", 200L, 150L})
        );

        // Test
        Object[] result = relatorioService.getDadosCampanha();

        // Assertion
        assertEquals(2, result.length);
        assertEquals("Flu Vaccination", ((Map<String, Object>) result[0]).get("campanha"));
        assertEquals(100L, ((Map<String, Object>) result[0]).get("quantidadeVacinacoes"));
        assertEquals(80L, ((Map<String, Object>) result[0]).get("quantidadeVacinados"));
        assertEquals("Covid Vaccination", ((Map<String, Object>) result[1]).get("campanha"));
        assertEquals(200L, ((Map<String, Object>) result[1]).get("quantidadeVacinacoes"));
        assertEquals(150L, ((Map<String, Object>) result[1]).get("quantidadeVacinados"));
    }

    @Test
    void testGetDadosStatusCampanha() {
        // Mocking
        when(vacinacaoService.contarQuantidadeTotalPorCampanha(1)).thenReturn(100);
        when(vacinacaoService.contarQuantidadePorStatusCampanha(1, StatusVacinacaoEnum.PENDENTE)).thenReturn(30);
        when(vacinacaoService.contarQuantidadePorStatusCampanha(1, StatusVacinacaoEnum.CONCLUIDO)).thenReturn(50);

        // Test
        Object[] result = relatorioService.getDadosStatusCampanha(1);

        // Assertion
        assertEquals(4, result.length);
        assertEquals("PENDENTE", ((Map<String, Object>) result[0]).get("status"));
        assertEquals(30, ((Map<String, Object>) result[0]).get("quantidade"));
        assertEquals(30.0, ((Map<String, Object>) result[0]).get("porcentagem"));
        assertEquals("CONCLUIDO", ((Map<String, Object>) result[1]).get("status"));
        assertEquals(50, ((Map<String, Object>) result[1]).get("quantidade"));
        assertEquals(50.0, ((Map<String, Object>) result[1]).get("porcentagem"));
    }

    @Test
    void testGetDadosStatusExames() {
        // Mocking
        when(exameService.conteQuantidadePorStatusExame()).thenReturn(
                List.of(new Object[]{StatusExameEnum.PENDENTE, 20L}, new Object[]{StatusExameEnum.CONCLUIDO, 40L})
        );

        // Test
        Object[] result = relatorioService.getDadosStatusExames();

        // Assertion
        assertEquals(2, result.length);
        assertEquals("PENDENTE", ((Map<String, Object>) result[0]).get("statusExame"));
        assertEquals(20L, ((Map<String, Object>) result[0]).get("quantidade"));
        assertEquals("CONCLUIDO", ((Map<String, Object>) result[1]).get("statusExame"));
        assertEquals(40L, ((Map<String, Object>) result[1]).get("quantidade"));
    }
}

