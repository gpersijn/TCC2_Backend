package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.request.ExameRequestDTO;
import net.javaguides.springboot.domain.dtos.response.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;
import net.javaguides.springboot.domain.repository.ExameRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExameServiceTest {

    @Mock
    private ExameRepository exameRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private ExameService exameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        // Mocking
        Exame exame = new Exame();
        when(exameRepository.findById(1)).thenReturn(Optional.of(exame));

        // Test
        Exame result = exameService.findById(1);

        // Assertion
        assertEquals(exame, result);
    }

    @Test
    void testFindById_ObjectNotFoundException() {
        // Mocking
        when(exameRepository.findById(1)).thenReturn(Optional.empty());

        // Test and Assertion
        assertThrows(ObjectNotFoundException.class, () -> exameService.findById(1));
    }

    @Test
    void testFindAll() {
        // Mocking
        List<Exame> exames = new ArrayList<>();
        exames.add(new Exame());
        exames.add(new Exame());
        when(exameRepository.findAll()).thenReturn(exames);

        // Test
        List<Exame> result = exameService.findAll();

        // Assertion
        assertEquals(exames, result);
    }

    @Test
    void testCreate() {
        // Mocking
        ExameRequestDTO requestDTO = new ExameRequestDTO();
        requestDTO.setIdPessoa(1);

        Pessoa pessoa = new Funcionario();
        when(pessoaRepository.findById(1)).thenReturn(Optional.of(pessoa));

        Exame createdExame = new Exame();
        createdExame.setStatusExame(StatusExameEnum.PENDENTE);
        when(exameRepository.save(any(Exame.class))).thenReturn(createdExame);

        // Test
        Exame result = exameService.create(requestDTO);

        // Assertion
        assertNotNull(result);
        assertEquals(StatusExameEnum.PENDENTE, result.getStatusExame());
        verify(exameRepository, times(1)).save(any(Exame.class));
    }

    @Test
    void testUpdate() {
        // Mocking
        ExameResponseDTO responseDTO = new ExameResponseDTO();
        responseDTO.setHoraExame(LocalTime.parse("10:00"));

        Exame oldExame = new Exame();
        oldExame.setIdExame(1);

        when(exameRepository.findById(1)).thenReturn(Optional.of(oldExame));
        when(exameRepository.save(any(Exame.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        Exame result = exameService.update(1, responseDTO);

        // Assertion
        assertEquals(oldExame.getIdExame(), result.getIdExame());
        assertEquals(responseDTO.getHoraExame(), result.getHoraExame());
    }

    @Test
    void testUpdateWithAllFields() {
        // Mocking
        ExameResponseDTO responseDTO = new ExameResponseDTO();
        responseDTO.setStatusExame(StatusExameEnum.EXPIRADO);
        responseDTO.setHoraExame(LocalTime.parse("10:00"));
        responseDTO.setDataExame(LocalDate.now());
        responseDTO.setNomeExame("Exame de Sangue");
        responseDTO.setLocalExame("Laboratório A");
        responseDTO.setTipoExame(TipoExameEnum.COMPLEMENTAR);

        Exame oldExame = new Exame();
        oldExame.setIdExame(1);

        when(exameRepository.findById(1)).thenReturn(Optional.of(oldExame));
        when(exameRepository.save(any(Exame.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        Exame result = exameService.update(1, responseDTO);

        // Assertion
        assertEquals(oldExame.getIdExame(), result.getIdExame());
        assertEquals(responseDTO.getStatusExame(), result.getStatusExame());
        assertEquals(responseDTO.getHoraExame(), result.getHoraExame());
        assertEquals(responseDTO.getDataExame(), result.getDataExame());
        assertEquals(responseDTO.getNomeExame(), result.getNomeExame());
        assertEquals(responseDTO.getLocalExame(), result.getLocalExame());
        assertEquals(responseDTO.getTipoExame(), result.getTipoExame());
    }

    @Test
    void testDelete() {
        // Test
        exameService.delete(1);

        // Assertion
        verify(exameRepository, times(1)).deleteById(1);
    }

    @Test
    void testFindExamesPorPessoa() {
        // Mocking
        List<ExameResponseDTO> exameResponseDTOList = new ArrayList<>();
        exameResponseDTOList.add(new ExameResponseDTO());
        exameResponseDTOList.add(new ExameResponseDTO());

        when(exameRepository.findByPessoaId(1)).thenReturn(exameResponseDTOList);

        // Test
        List<ExameResponseDTO> result = exameService.findExamesPorPessoa(1);

        // Assertion
        assertEquals(exameResponseDTOList, result);
    }

    @Test
    void testConteQuantidadePorTipoExame() {
        // Mocking
        List<Object[]> resultado = new ArrayList<>();
        resultado.add(new Object[]{StatusExameEnum.PENDENTE, 20L});
        resultado.add(new Object[]{StatusExameEnum.CONCLUIDO, 40L});

        when(exameRepository.listQuantidadeTiposExame()).thenReturn(resultado);

        // Test
        List<Object[]> result = exameService.conteQuantidadePorTipoExame();

        // Assertion
        assertEquals(resultado, result);
    }

    @Test
    void testConteQuantidadePorStatusExame() {
        // Mocking
        List<Object[]> resultado = new ArrayList<>();
        resultado.add(new Object[]{StatusExameEnum.PENDENTE, 20L});
        resultado.add(new Object[]{StatusExameEnum.CONCLUIDO, 40L});

        when(exameRepository.listQuantidadeStatusExame()).thenReturn(resultado);

        // Test
        List<Object[]> result = exameService.conteQuantidadePorStatusExame();

        // Assertion
        assertEquals(resultado, result);
    }
}

