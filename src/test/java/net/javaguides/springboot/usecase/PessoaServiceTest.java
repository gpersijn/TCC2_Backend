package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.SexoEnum;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PessoaServiceTest {
    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void testListPessoasAprovadas() {
        // Mocking
        when(pessoaRepository.findByIsApprovedTrue()).thenReturn(Arrays.asList(new Funcionario(), new Funcionario()));

        // Test
        List<Pessoa> result = pessoaService.listPessoasAprovadas();

        // Assertion
        assertEquals(2, result.size());
    }

    @Test
    void testContarHomens() {
        // Mocking
        when(pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.MASCULINO, true)).thenReturn(3);

        // Test
        Integer result = pessoaService.contarHomens();

        // Assertion
        assertEquals(3, result);
    }

    @Test
    void testContarMulheres() {
        // Mocking
        when(pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.FEMININO, true)).thenReturn(5);

        // Test
        Integer result = pessoaService.contarMulheres();

        // Assertion
        assertEquals(5, result);
    }

    @Test
    void testContarOutros() {
        // Mocking
        when(pessoaRepository.countBySexoEnumAndIsApproved(SexoEnum.OUTRO, true)).thenReturn(2);

        // Test
        Integer result = pessoaService.contarOutros();

        // Assertion
        assertEquals(2, result);
    }

    @Test
    void testContarPessoas() {
        // Mocking
        when(pessoaRepository.countByIsApprovedTrue()).thenReturn(10);

        // Test
        Integer result = pessoaService.contarPessoas();

        // Assertion
        assertEquals(10, result);
    }

    @Test
    void testContarPessoasPorSetor() {
        // Mocking
        Object[] setor1 = {"Setor1", 5};
        Object[] setor2 = {"Setor2", 8};
        when(pessoaRepository.countBySetor()).thenReturn(Arrays.asList(setor1, setor2));

        // Test
        List<Object[]> result = pessoaService.contarPessoasPorSetor();

        // Assertion
        assertEquals(2, result.size());
        assertEquals(setor1, result.get(0));
        assertEquals(setor2, result.get(1));
    }

}
