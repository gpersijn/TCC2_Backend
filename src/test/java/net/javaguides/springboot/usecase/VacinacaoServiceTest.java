package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestAtualizacaoDTO;
import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestDTO;
import net.javaguides.springboot.domain.dtos.response.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;
import net.javaguides.springboot.domain.repository.CampanhaRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.domain.repository.VacinacaoRepository;
import net.javaguides.springboot.mocks.VacinacaoDTOMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class VacinacaoServiceTest {

    @InjectMocks
    private VacinacaoService vacinacaoService;

    @Mock
    private VacinacaoRepository vacinacaoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private CampanhaRepository campanhaRepository;

    @Test
    void testFindById() {
        // Mocking
        when(vacinacaoRepository.findById(1)).thenReturn(Optional.of(new Vacinacao()));

        // Test
        Vacinacao result = vacinacaoService.findById(1);

        // Assertion
        assertNotNull(result);
    }

    @Test
    void testFindAll() {
        // Mocking
        when(vacinacaoRepository.findAll()).thenReturn(Arrays.asList(new Vacinacao(), new Vacinacao()));

        // Test
        List<Vacinacao> result = vacinacaoService.findAll();

        // Assertion
        assertEquals(2, result.size());
    }

    @Test
    void testCreate() {
        // Mocking
        VacinacaoRequestDTO requestDTO = VacinacaoDTOMock.withRequestValues();

        Campanha campanha = new Campanha();
        campanha.setId(1);
        when(campanhaRepository.findById(requestDTO.getIdCampanha())).thenReturn(Optional.of(campanha));

        Pessoa pessoa1 = new Funcionario();
        pessoa1.setId(1);
        Pessoa pessoa2 = new Funcionario();
        pessoa2.setId(2);
        when(pessoaRepository.findAllById(requestDTO.getIdFuncionarios())).thenReturn(Arrays.asList(pessoa1, pessoa2));

        // Mock para o saveAll
        when(vacinacaoRepository.saveAll(Mockito.anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        List<Vacinacao> result = vacinacaoService.create(requestDTO);

        // Assertion
        assertEquals(2, result.size());
        assertEquals(campanha, result.get(0).getCampanha());
        assertEquals(pessoa1, result.get(0).getPessoa());
        assertEquals(StatusVacinacaoEnum.PENDENTE, result.get(0).getStatus());
    }

    @Test
    void testUpdate() {
        // Mocking
        VacinacaoRequestAtualizacaoDTO requestDTO = VacinacaoDTOMock.withUpdateValues();

        Vacinacao oldVacinacao = new Vacinacao();
        oldVacinacao.setId(1);
        oldVacinacao.setStatus(StatusVacinacaoEnum.PENDENTE);

        when(vacinacaoRepository.findById(1)).thenReturn(Optional.of(oldVacinacao));
        when(vacinacaoRepository.save(Mockito.any(Vacinacao.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        Vacinacao updatedVacinacao = vacinacaoService.update(1, requestDTO);

        // Assertion
        assertEquals(oldVacinacao.getId(), updatedVacinacao.getId());
        assertEquals(requestDTO.getStatusVacinacao(), updatedVacinacao.getStatus());
    }

    @Test
    void testDelete() {
        // Test
        assertDoesNotThrow(() -> vacinacaoService.delete(1));

        // Assertion
        verify(vacinacaoRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindVacinacoesPorPessoa() {
        // Mocking
        when(vacinacaoRepository.findByPessoaId(1)).thenReturn(Arrays.asList(VacinacaoDTOMock.withResponseValues(), VacinacaoDTOMock.withResponseValues()));

        // Test
        List<VacinacaoResponseDTO> result = vacinacaoService.findVacinacoesPorPessoa(1);

        // Assertion
        assertEquals(2, result.size());
    }

    @Test
    void testListContagemVacinacoesPorCampanha() {
        // Mocking
        when(vacinacaoRepository.listCountVacinacoesPorCampanha()).thenReturn(Arrays.asList(new Object[]{1L, 5}, new Object[]{2L, 8}));

        // Test
        List<Object[]> result = vacinacaoService.listContagemVacinacoesPorCampanha();

        // Assertion
        assertEquals(2, result.size());
    }

    @Test
    void testContarQuantidadePorStatusCampanha() {
        // Mocking
        when(vacinacaoRepository.contarQuantidadePorStatusCampanha(1, StatusVacinacaoEnum.PENDENTE)).thenReturn(3);

        // Test
        Integer result = vacinacaoService.contarQuantidadePorStatusCampanha(1, StatusVacinacaoEnum.PENDENTE);

        // Assertion
        assertEquals(3, result);
    }

    @Test
    void testContarQuantidadeTotalPorCampanha() {
        // Mocking
        when(vacinacaoRepository.contarQuantidadeTotalPorCampanha(1)).thenReturn(10);

        // Test
        Integer result = vacinacaoService.contarQuantidadeTotalPorCampanha(1);

        // Assertion
        assertEquals(10, result);
    }
}
