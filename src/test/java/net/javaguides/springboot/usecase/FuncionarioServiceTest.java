package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.repository.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FuncionarioServiceTest {

    @InjectMocks
    private FuncionarioService funcionarioService;

    @Mock
    private FuncionarioRepository funcionarioRepository;

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
        when(funcionarioRepository.save(Mockito.any(Funcionario.class))).thenReturn(funcionario);

        Funcionario result = funcionarioService.aprovarLogin(1);

        assertNotNull(result);
        assertTrue(result.getIsApproved());
    }
}