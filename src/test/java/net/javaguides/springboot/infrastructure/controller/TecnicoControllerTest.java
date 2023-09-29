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
    public void testFindById() {
        // Arrange
        int tecnicoId = 1;
        Tecnico tecnico = new Tecnico(); // Preencha com os dados do técnico
        when(tecnicoService.findById(tecnicoId)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.findById(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).findById(tecnicoId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Tecnico> tecnicos = new ArrayList<>(); // Preencha com os dados dos técnicos
        when(tecnicoService.findAll()).thenReturn(tecnicos);

        // Act
        ResponseEntity<List<TecnicoDTO>> response = tecnicoController.findAll();

        // Assert
        verify(tecnicoService, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testUpdate() {
        // Arrange
        int tecnicoId = 1;
        TecnicoDTO tecnicoDTO = new TecnicoDTO(); // Preencha com os dados do DTO
        Tecnico tecnico = new Tecnico(); // Preencha com os dados do técnico atualizado
        when(tecnicoService.update(tecnicoId, tecnicoDTO)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.update(tecnicoId, tecnicoDTO);

        // Assert
        verify(tecnicoService, times(1)).update(tecnicoId, tecnicoDTO);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testDelete() {
        // Arrange
        int tecnicoId = 1;

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.delete(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).delete(tecnicoId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    public void testAddPerfil() {
        // Arrange
        PerfilDTO perfilDTO = new PerfilDTO(); // Preencha com os dados do perfil
        Tecnico tecnico = new Tecnico(); // Preencha com os dados do técnico
        when(tecnicoService.adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum())).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.addPerfil(perfilDTO);

        // Assert
        verify(tecnicoService, times(1)).adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum());
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testListarTecnicosNaoAprovados() {
        // Arrange
        List<Tecnico> tecnicosNaoAprovados = new ArrayList<>(); // Preencha com os dados dos técnicos não aprovados
        when(tecnicoService.listTecnicosNaoAprovados()).thenReturn(tecnicosNaoAprovados);

        // Act
        ResponseEntity<List<Tecnico>> response = tecnicoController.listarTecnicosNaoAprovados();

        // Assert
        verify(tecnicoService, times(1)).listTecnicosNaoAprovados();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testAprovar() {
        // Arrange
        int tecnicoId = 1;
        Tecnico tecnico = new Tecnico(); // Preencha com os dados do técnico aprovado
        when(tecnicoService.aprovarLogin(tecnicoId)).thenReturn(tecnico);

        // Act
        ResponseEntity<TecnicoDTO> response = tecnicoController.aprovar(tecnicoId);

        // Assert
        verify(tecnicoService, times(1)).aprovarLogin(tecnicoId);
        assert response.getStatusCode() == HttpStatus.OK;
    }
}

