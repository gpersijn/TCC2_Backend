package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.repository.ASORepository;
import net.javaguides.springboot.domain.repository.ExameRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.mocks.AsoDTOMock;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class ASOServiceTest {
    @InjectMocks
    private ASOService asoService;

    @Mock
    private ASORepository asoRepository;

    @Mock
    private ExameRepository exameRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    public void testFindById() {
        ASO aso = new ASO();
        aso.setIdASO(1);
        when(asoRepository.findById(1)).thenReturn(Optional.of(aso));

        ASO result = asoService.findById(1);

        assertEquals(1, result.getIdASO());
    }

    @Test
    public void testFindByIdNotFound() {
        when(asoRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> asoService.findById(1));
    }

    @Test
    public void testFindAll() {
        ASO aso1 = new ASO();
        ASO aso2 = new ASO();
        when(asoRepository.findAll()).thenReturn(List.of(aso1, aso2));

        List<ASO> result = asoService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testCreate() {
        ASORequestDTO dto = new ASORequestDTO();
        dto.setIdASO(null);
        dto.setExames(new ArrayList<>());
        when(exameRepository.findAllById(dto.getExames())).thenReturn(new ArrayList<>());
        when(pessoaRepository.findById(dto.getIdPessoa())).thenReturn(Optional.of(new Funcionario()));
        when(asoRepository.save(Mockito.any(ASO.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ASO createdASO = asoService.create(dto);

        assertEquals(dto.getIdASO(), createdASO.getIdASO());
    }

    @Test
    public void testUpdate() {
        ASORequestDTO dto = AsoDTOMock.withDefaultValuesRequest();
        Pessoa mockPessoa = new Funcionario();

        ASO oldASO = new ASO();
        oldASO.setIdASO(1);

        when(asoRepository.findById(1)).thenReturn(Optional.of(oldASO));

        Exame exame1 = new Exame();
        exame1.setIdExame(1);
        Exame exame2 = new Exame();
        exame2.setIdExame(2);
        Exame exame3 = new Exame();
        exame3.setIdExame(3);

        Mockito.when(exameRepository.findAllById(dto.getExames())).thenReturn(List.of(exame1, exame2, exame3));
        Mockito.when(pessoaRepository.findById(1)).thenReturn(Optional.of(mockPessoa));
        when(asoRepository.save(any(ASO.class))).thenAnswer(invocation -> {
            ASO savedASO = invocation.getArgument(0);
            savedASO.setIdASO(1);
            return savedASO;
        });

        ASO updatedASO = asoService.update(1, dto);

        assertEquals(dto.getCnpj(), updatedASO.getCnpj());
        assertEquals(dto.getNomeEmpresa(), updatedASO.getNomeEmpresa());
        assertEquals(mockPessoa, updatedASO.getPessoa());
        assertEquals(dto.getRisco(), updatedASO.getRisco());
        assertEquals(dto.getExames().size(), updatedASO.getExames().size());
        assertEquals(dto.getNomeMedicoPCMSO(), updatedASO.getNomeMedicoPCMSO());
        assertEquals(dto.getCrmMedicoPCMSO(), updatedASO.getCrmMedicoPCMSO());
        assertEquals(dto.getNomeMedicoClinico(), updatedASO.getNomeMedicoClinico());
        assertEquals(dto.getCrmMedicoClinico(), updatedASO.getCrmMedicoClinico());
        assertEquals(dto.getResultadoASO(), updatedASO.getResultadoASO());
        assertEquals(dto.getValidade(), updatedASO.getValidade());
        assertEquals(dto.getDataASO(), updatedASO.getDataASO());
    }

    @Test
    public void testUpdateWithNullValues() {
        ASORequestDTO dto = new ASORequestDTO();
        Pessoa mockPessoa = new Funcionario();

        ASO oldASO = new ASO();
        oldASO.setIdASO(1);

        when(asoRepository.findById(1)).thenReturn(Optional.of(oldASO));

        Exame exame1 = new Exame();
        exame1.setIdExame(1);
        Exame exame2 = new Exame();
        exame2.setIdExame(2);
        Exame exame3 = new Exame();
        exame3.setIdExame(3);

        Mockito.when(exameRepository.findAllById(dto.getExames())).thenReturn(List.of(exame1, exame2, exame3));
        Mockito.when(pessoaRepository.findById(1)).thenReturn(Optional.of(mockPessoa));
        when(asoRepository.save(any(ASO.class))).thenAnswer(invocation -> {
            ASO savedASO = invocation.getArgument(0);
            savedASO.setIdASO(1);
            return savedASO;
        });

        ASO updatedASO = asoService.update(1, dto);

        assertEquals(dto.getCnpj(), updatedASO.getCnpj());
        assertEquals(dto.getNomeEmpresa(), updatedASO.getNomeEmpresa());
        assertEquals(dto.getRisco(), updatedASO.getRisco());
        assertEquals(dto.getNomeMedicoPCMSO(), updatedASO.getNomeMedicoPCMSO());
        assertEquals(dto.getCrmMedicoPCMSO(), updatedASO.getCrmMedicoPCMSO());
        assertEquals(dto.getNomeMedicoClinico(), updatedASO.getNomeMedicoClinico());
        assertEquals(dto.getCrmMedicoClinico(), updatedASO.getCrmMedicoClinico());
        assertEquals(dto.getResultadoASO(), updatedASO.getResultadoASO());
        assertEquals(dto.getValidade(), updatedASO.getValidade());
        assertEquals(dto.getDataASO(), updatedASO.getDataASO());
    }

    @Test
    public void testDelete() {
        asoService.delete(1);

        verify(asoRepository, times(1)).deleteById(1);
    }
}


