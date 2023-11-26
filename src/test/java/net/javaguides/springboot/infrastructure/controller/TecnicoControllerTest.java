package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.PerfilDTO;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.usecase.TecnicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class TecnicoControllerTest {

    @InjectMocks
    private TecnicoController tecnicoController;

    @Mock
    private TecnicoService tecnicoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        // Arrange
        int tecnicoId = 1;
        Tecnico tecnico = new Tecnico();
        when(tecnicoService.findById(tecnicoId)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.findById(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).findById(tecnicoId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Tecnico> tecnicos = new ArrayList<>();
        when(tecnicoService.findAll()).thenReturn(tecnicos);

        // Act
        ResponseEntity<List<TecnicoDTO>> response = tecnicoController.findAll();

        // Assert
        verify(tecnicoService, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testUpdate() {
        // Arrange
        int tecnicoId = 1;
        TecnicoDTO tecnicoDTO = new TecnicoDTO();
        Tecnico tecnico = new Tecnico();
        when(tecnicoService.update(tecnicoId, tecnicoDTO)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.update(tecnicoId, tecnicoDTO);

        // Assert
        verify(tecnicoService, times(1)).update(tecnicoId, tecnicoDTO);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testDelete() {
        // Arrange
        int tecnicoId = 1;

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.delete(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).delete(tecnicoId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    void testAddPerfil() {
        // Arrange
        PerfilDTO perfilDTO = new PerfilDTO();
        Tecnico tecnico = new Tecnico();
        when(tecnicoService.adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum())).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.addPerfil(perfilDTO);

        // Assert
        verify(tecnicoService, times(1)).adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum());
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testListarTecnicosNaoAprovados() {
        // Arrange
        List<Tecnico> tecnicosNaoAprovados = new ArrayList<>();
        when(tecnicoService.listTecnicosNaoAprovados()).thenReturn(tecnicosNaoAprovados);

        // Act
        ResponseEntity<List<Tecnico>> response = tecnicoController.listarTecnicosNaoAprovados();

        // Assert
        verify(tecnicoService, times(1)).listTecnicosNaoAprovados();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    void testAprovar() {
        // Arrange
        int tecnicoId = 1;
        Tecnico tecnico = new Tecnico();
        when(tecnicoService.aprovarLogin(tecnicoId)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.aprovar(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).aprovarLogin(tecnicoId);
        assert response.getStatusCode() == HttpStatus.OK;
    }
}

