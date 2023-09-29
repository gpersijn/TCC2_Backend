package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.domain.repository.TecnicoRepository;
import net.javaguides.springboot.mocks.TecnicoDTOMock;
import net.javaguides.springboot.usecase.exceptions.DataViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TecnicoServiceTest {

    @InjectMocks
    private TecnicoService tecnicoService;

    @Mock
    private TecnicoRepository tecnicoRepository;

    @Mock
    private TecnicoDTO mockDTO;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private BCryptPasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        Tecnico tecnico = new Tecnico();
        tecnico.setId(1);

        when(tecnicoRepository.findById(1)).thenReturn(Optional.of(tecnico));

        Tecnico result = tecnicoService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testFindAll() {
        Tecnico tec1 = new Tecnico();
        Tecnico tec2 = new Tecnico();
        when(tecnicoRepository.findAll()).thenReturn(List.of(tec2, tec1));

        List<Tecnico> result = tecnicoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteTecnico() {
        Tecnico existingTecnico = new Tecnico();
        existingTecnico.setId(1);

        when(tecnicoRepository.findById(1)).thenReturn(Optional.of(existingTecnico));

        assertDoesNotThrow(() -> tecnicoService.delete(1));
    }

    @Test
    public void testAdicionarPerfil() {
        Tecnico tecnico = new Tecnico();
        tecnico.setEmail("test@example.com");
        tecnico.addPerfil(PerfilEnum.ADMIN);

        when(tecnicoRepository.findByEmail("test@example.com")).thenReturn(Optional.of(tecnico));

        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            tecnicoService.adicionarPerfil("test@example.com", PerfilEnum.ADMIN);
        });

        assertEquals("O usuário já possui permissões do perfil selecionado!", dataIntegrityViolationException.getMessage());
    }

    @Test
    public void testFindByEmail() {
        Tecnico tecnico = new Tecnico();
        tecnico.setEmail("test@example.com");

        when(tecnicoRepository.findByEmail("test@example.com")).thenReturn(Optional.of(tecnico));

        Tecnico result = tecnicoService.findByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testAprovarLogin() {
        Tecnico tecnico = new Tecnico();
        tecnico.setId(1);
        tecnico.setIsApproved(false);

        when(tecnicoRepository.findById(1)).thenReturn(Optional.of(tecnico));
        when(tecnicoRepository.save(Mockito.any(Tecnico.class))).thenReturn(tecnico);

        Tecnico result = tecnicoService.aprovarLogin(1);

        assertNotNull(result);
        assertTrue(result.getIsApproved());
    }

    @Test
    public void testAtualizarValoresTodosCamposPreenchidos() {
        TecnicoDTO dto = new TecnicoDTO();
        dto.setPrimeiroNome("NovoPrimeiroNome");
        dto.setUltimoNome("NovoUltimoNome");
        dto.setEmail("novoemail@example.com");
        dto.setSetor("NovoSetor");
        dto.setTelefone("NovoTelefone");
        dto.setDataAniversario(LocalDate.of(1990, 1, 1));
        dto.setSexoEnum(SexoEnum.MASCULINO);
        dto.setCpf("12345678901");

        Tecnico oldTecnico = new Tecnico();
        TecnicoService.atualizarValores(dto, oldTecnico);

        assertEquals("NovoPrimeiroNome", oldTecnico.getPrimeiroNome());
        assertEquals("NovoUltimoNome", oldTecnico.getUltimoNome());
        assertEquals("novoemail@example.com", oldTecnico.getEmail());
        assertEquals("NovoSetor", oldTecnico.getSetor());
        assertEquals("NovoTelefone", oldTecnico.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), oldTecnico.getDataAniversario());
        assertEquals(SexoEnum.MASCULINO, oldTecnico.getSexoEnum());
        assertEquals("12345678901", oldTecnico.getCpf());
    }

    @Test
    public void testAtualizarValoresNenhumCampoPreenchido() {
        TecnicoDTO dto = new TecnicoDTO();

        Tecnico oldTecnico = new Tecnico();
        oldTecnico.setPrimeiroNome("AntigoPrimeiroNome");
        oldTecnico.setUltimoNome("AntigoUltimoNome");
        oldTecnico.setEmail("antigoemail@example.com");
        oldTecnico.setSetor("AntigoSetor");
        oldTecnico.setTelefone("AntigoTelefone");
        oldTecnico.setDataAniversario(LocalDate.of(1980, 1, 1));
        oldTecnico.setSexoEnum(SexoEnum.FEMININO);
        oldTecnico.setCpf("98765432109");

        TecnicoService.atualizarValores(dto, oldTecnico);

        assertEquals("AntigoPrimeiroNome", oldTecnico.getPrimeiroNome());
        assertEquals("AntigoUltimoNome", oldTecnico.getUltimoNome());
        assertEquals("antigoemail@example.com", oldTecnico.getEmail());
        assertEquals("AntigoSetor", oldTecnico.getSetor());
        assertEquals("AntigoTelefone", oldTecnico.getTelefone());
        assertEquals(LocalDate.of(1980, 1, 1), oldTecnico.getDataAniversario());
        assertEquals(SexoEnum.FEMININO, oldTecnico.getSexoEnum());
        assertEquals("98765432109", oldTecnico.getCpf());
    }

    @Test
    public void testValidaCpfEmailEmailAlreadyExists() {
        TecnicoDTO dto = new TecnicoDTO();
        dto.setId(1);

        when(pessoaRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Tecnico()));

        assertThrows(DataViolationException.class, () -> {
            tecnicoService.validaCpfEmail(dto);
        });
    }

    @Test
    public void testValidaCpfEmailCpfAlreadyExists() {
        TecnicoDTO dto = new TecnicoDTO();
        dto.setId(1);

        when(pessoaRepository.findByCpf(dto.getCpf())).thenReturn(Optional.of(new Tecnico()));

        assertThrows(DataViolationException.class, () -> {
            tecnicoService.validaCpfEmail(dto);
        });
    }

    @Test
    public void testCreate() {
        TecnicoDTO dto = TecnicoDTOMock.withDefaultValues();
        when(encoder.encode(dto.getSenha())).thenReturn("hashedPassword");
        when(tecnicoRepository.save(any(Tecnico.class))).thenReturn(new Tecnico(dto));

        Tecnico createdTecnico = tecnicoService.create(dto);

        assertNotNull(createdTecnico);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        TecnicoDTO dto = TecnicoDTOMock.withDefaultValues();
        when(tecnicoRepository.findById(id)).thenReturn(Optional.of(new Tecnico()));
        when(tecnicoRepository.save(any(Tecnico.class))).thenReturn(new Tecnico(dto));

        Tecnico updatedTecnico = tecnicoService.update(id, dto);

        assertNotNull(updatedTecnico);
        assertEquals(dto.getId(), updatedTecnico.getId());
    }

}

