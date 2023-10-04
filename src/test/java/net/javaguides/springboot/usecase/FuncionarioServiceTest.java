package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;
import net.javaguides.springboot.domain.repository.FuncionarioRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.mocks.FuncionarioDTOMock;
import net.javaguides.springboot.usecase.exceptions.DataViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private BCryptPasswordEncoder encoder;


    @Test
    public void testFindById() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1);

        when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));

        Funcionario result = funcionarioService.findById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    public void testFindAll() {
        Funcionario fun1 = new Funcionario();
        Funcionario fun2 = new Funcionario();
        when(funcionarioRepository.findAll()).thenReturn(List.of(fun2, fun1));

        List<Funcionario> result = funcionarioService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testDeleteFuncionario() {
        Funcionario existingFuncionario = new Funcionario();
        existingFuncionario.setId(1);

        when(funcionarioRepository.findById(1)).thenReturn(Optional.of(existingFuncionario));

        assertDoesNotThrow(() -> funcionarioService.delete(1));
    }

    @Test
    public void testAdicionarPerfil() {
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("test@example.com");
        funcionario.addPerfil(PerfilEnum.ADMIN);

        when(funcionarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(funcionario));

        DataIntegrityViolationException dataIntegrityViolationException = assertThrows(DataIntegrityViolationException.class, () -> {
            funcionarioService.adicionarPerfil("test@example.com", PerfilEnum.ADMIN);
        });

        assertEquals("O usuário já possui permissões do perfil selecionado!", dataIntegrityViolationException.getMessage());
    }

    @Test
    public void testFindByEmail() {
        Funcionario funcionario = new Funcionario();
        funcionario.setEmail("test@example.com");

        when(funcionarioRepository.findByEmail("test@example.com")).thenReturn(Optional.of(funcionario));

        Funcionario result = funcionarioService.findByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testAprovarLogin() {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1);
        funcionario.setIsApproved(false);

        when(funcionarioRepository.findById(1)).thenReturn(Optional.of(funcionario));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);

        Funcionario result = funcionarioService.aprovarLogin(1);

        assertNotNull(result);
        assertTrue(result.getIsApproved());
    }

    @Test
    public void testAtualizarValoresTodosCamposPreenchidos() {
        // Crie um mock de FuncionarioDTO com valores reais preenchidos em todos os campos
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setPrimeiroNome("NovoPrimeiroNome");
        dto.setUltimoNome("NovoUltimoNome");
        dto.setEmail("novoemail@example.com");
        dto.setSetor("NovoSetor");
        dto.setTelefone("NovoTelefone");
        dto.setDataAniversario(LocalDate.of(1990, 1, 1));
        dto.setSexoEnum(SexoEnum.MASCULINO);
        dto.setCpf("12345678901");

        Funcionario oldFuncionario = new Funcionario();
        FuncionarioService.atualizarValores(dto, oldFuncionario);

        assertEquals("NovoPrimeiroNome", oldFuncionario.getPrimeiroNome());
        assertEquals("NovoUltimoNome", oldFuncionario.getUltimoNome());
        assertEquals("novoemail@example.com", oldFuncionario.getEmail());
        assertEquals("NovoSetor", oldFuncionario.getSetor());
        assertEquals("NovoTelefone", oldFuncionario.getTelefone());
        assertEquals(LocalDate.of(1990, 1, 1), oldFuncionario.getDataAniversario());
        assertEquals(SexoEnum.MASCULINO, oldFuncionario.getSexoEnum());
        assertEquals("12345678901", oldFuncionario.getCpf());
    }

    @Test
    public void testAtualizarValoresNenhumCampoPreenchido() {
        FuncionarioDTO dto = new FuncionarioDTO();

        Funcionario oldFuncionario = new Funcionario();
        oldFuncionario.setPrimeiroNome("AntigoPrimeiroNome");
        oldFuncionario.setUltimoNome("AntigoUltimoNome");
        oldFuncionario.setEmail("antigoemail@example.com");
        oldFuncionario.setSetor("AntigoSetor");
        oldFuncionario.setTelefone("AntigoTelefone");
        oldFuncionario.setDataAniversario(LocalDate.of(1980, 1, 1));
        oldFuncionario.setSexoEnum(SexoEnum.FEMININO);
        oldFuncionario.setCpf("98765432109");

        FuncionarioService.atualizarValores(dto, oldFuncionario);

        assertEquals("AntigoPrimeiroNome", oldFuncionario.getPrimeiroNome());
        assertEquals("AntigoUltimoNome", oldFuncionario.getUltimoNome());
        assertEquals("antigoemail@example.com", oldFuncionario.getEmail());
        assertEquals("AntigoSetor", oldFuncionario.getSetor());
        assertEquals("AntigoTelefone", oldFuncionario.getTelefone());
        assertEquals(LocalDate.of(1980, 1, 1), oldFuncionario.getDataAniversario());
        assertEquals(SexoEnum.FEMININO, oldFuncionario.getSexoEnum());
        assertEquals("98765432109", oldFuncionario.getCpf());
    }

    @Test
    public void testValidaCpfEmailEmailAlreadyExists() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setIdFuncionario(1); // Set the ID to a value that would not match any existing Pessoa

        when(pessoaRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(new Funcionario()));

        assertThrows(DataViolationException.class, () -> {
            funcionarioService.validaCpfEmail(dto);
        });
    }

    @Test
    public void testValidaCpfEmailCpfAlreadyExists() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setIdFuncionario(1);

        when(pessoaRepository.findByCpf(dto.getCpf())).thenReturn(Optional.of(new Funcionario()));

        assertThrows(DataViolationException.class, () -> {
            funcionarioService.validaCpfEmail(dto);
        });
    }

    @Test
    public void testCreate() {
        FuncionarioDTO dto = FuncionarioDTOMock.withDefaultValues();
        when(encoder.encode(dto.getSenha())).thenReturn("hashedPassword");
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario(dto));

        Funcionario createdFuncionario = funcionarioService.create(dto);

        assertNotNull(createdFuncionario);
    }

    @Test
    public void testUpdate() {
        Integer id = 1;
        FuncionarioDTO dto = FuncionarioDTOMock.withDefaultValues();
        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario()));
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(new Funcionario(dto));

        Funcionario updatedFuncionario = funcionarioService.update(id, dto);

        assertNotNull(updatedFuncionario);
        assertEquals(dto.getIdFuncionario(), updatedFuncionario.getId());
    }

}