package net.javaguides.springboot.usecase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import net.javaguides.springboot.domain.dtos.CampanhaDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.repository.CampanhaRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CampanhaServiceTest {

    @Mock
    private CampanhaRepository campanhaRepository;

    @InjectMocks
    private CampanhaService campanhaService;

    @Test
    void testFindById() {
        // Mocking
        Campanha campanha = new Campanha();
        when(campanhaRepository.findById(1)).thenReturn(Optional.of(campanha));

        // Test
        Campanha result = campanhaService.findById(1);

        // Assertion
        assertNotNull(result);
        assertEquals(campanha, result);
    }

    @Test
    void testFindById_ObjectNotFoundException() {
        // Mocking
        when(campanhaRepository.findById(1)).thenReturn(Optional.empty());

        // Test and Assertion
        assertThrows(ObjectNotFoundException.class, () -> campanhaService.findById(1));
    }

    @Test
    void testCreate() {
        // Mocking
        CampanhaDTO campanhaDTO = new CampanhaDTO();
        campanhaDTO.setNomeCampanha("Campanha de Teste");

        Campanha createdCampanha = new Campanha();
        when(campanhaRepository.save(any(Campanha.class))).thenReturn(createdCampanha);

        // Test
        Campanha result = campanhaService.create(campanhaDTO);

        // Assertion
        assertNotNull(result);
        assertEquals(createdCampanha, result);
    }

    @Test
    void testUpdate() {
        // Mocking
        CampanhaDTO campanhaDTO = new CampanhaDTO();
        campanhaDTO.setNomeCampanha("Campanha Atualizada");

        Campanha oldCampanha = new Campanha();
        oldCampanha.setId(1);

        when(campanhaRepository.findById(1)).thenReturn(Optional.of(oldCampanha));
        when(campanhaRepository.save(any(Campanha.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Test
        Campanha result = campanhaService.update(1, campanhaDTO);

        // Assertion
        assertNotNull(result);
        assertEquals(campanhaDTO.getNomeCampanha(), result.getNomeCampanha());
        assertEquals(oldCampanha.getId(), result.getId());
    }

    @Test
    void testUpdate_ObjectNotFoundException() {
        // Mocking
        CampanhaDTO campanhaDTO = new CampanhaDTO();
        campanhaDTO.setNomeCampanha("Campanha Atualizada");

        when(campanhaRepository.findById(1)).thenReturn(Optional.empty());

        // Test and Assertion
        assertThrows(ObjectNotFoundException.class, () -> campanhaService.update(1, campanhaDTO));
    }

    @Test
    void testDelete() {
        // Mocking
        doNothing().when(campanhaRepository).deleteById(1);

        // Test
        campanhaService.delete(1);

        // Verify that deleteById was called
        verify(campanhaRepository, times(1)).deleteById(1);
    }
}
