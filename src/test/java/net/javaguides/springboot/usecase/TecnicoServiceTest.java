package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.repository.TecnicoRepository;
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
public class TecnicoServiceTest {

    @InjectMocks
    private TecnicoService tecnicoService;

    @Mock
    private TecnicoRepository tecnicoRepository;

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
}
