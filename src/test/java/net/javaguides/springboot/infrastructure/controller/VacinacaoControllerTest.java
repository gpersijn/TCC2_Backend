package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestAtualizacaoDTO;
import net.javaguides.springboot.domain.dtos.request.VacinacaoRequestDTO;
import net.javaguides.springboot.domain.dtos.response.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.mocks.VacinacaoDTOMock;
import net.javaguides.springboot.usecase.VacinacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class VacinacaoControllerTest {

    @InjectMocks
    private VacinacaoController vacinacaoController;

    @Mock
    private VacinacaoService vacinacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        int vacinacaoId = 1;
        Vacinacao vacinacao = new Vacinacao();
        when(vacinacaoService.findById(vacinacaoId)).thenReturn(vacinacao);

        // Act
        ResponseEntity<VacinacaoResponseDTO> response = vacinacaoController.findById(vacinacaoId);

        // Assert
        verify(vacinacaoService, times(1)).findById(vacinacaoId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testFindAllVacinacoesPorPessoa() {
        // Arrange
        int idPessoa = 1;
        List<VacinacaoResponseDTO> vacinacoes = new ArrayList<>();
        when(vacinacaoService.findVacinacoesPorPessoa(idPessoa)).thenReturn(vacinacoes);

        // Act
        ResponseEntity<List<VacinacaoResponseDTO>> response = vacinacaoController.findAllVacinacoesPorPessoa(idPessoa);

        // Assert
        verify(vacinacaoService, times(1)).findVacinacoesPorPessoa(idPessoa);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Vacinacao> vacinacoes = new ArrayList<>();
        when(vacinacaoService.findAll()).thenReturn(vacinacoes);

        // Act
        ResponseEntity<List<VacinacaoResponseDTO>> response = vacinacaoController.findAll();

        // Assert
        verify(vacinacaoService, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testCreate() {
        // Arrange
        VacinacaoRequestDTO vacinacaoDTO = VacinacaoDTOMock.withRequestValues();
        List<Vacinacao> vacinacoes = new ArrayList<>();
        when(vacinacaoService.create(vacinacaoDTO)).thenReturn(vacinacoes);

        // Act
        ResponseEntity<List<Vacinacao>> response = vacinacaoController.create(vacinacaoDTO);

        // Assert
        verify(vacinacaoService, times(1)).create(vacinacaoDTO);
        assert response.getStatusCode() == HttpStatus.CREATED;
    }

    @Test
    public void testUpdate() {
        // Arrange
        int vacinacaoId = 1;
        VacinacaoRequestAtualizacaoDTO vacinacaoDTO = new VacinacaoRequestAtualizacaoDTO();
        Vacinacao vacinacao = new Vacinacao();
        when(vacinacaoService.update(vacinacaoId, vacinacaoDTO)).thenReturn(vacinacao);

        // Act
        ResponseEntity<VacinacaoResponseDTO> response = vacinacaoController.update(vacinacaoId, vacinacaoDTO);

        // Assert
        verify(vacinacaoService, times(1)).update(vacinacaoId, vacinacaoDTO);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testDelete() {
        // Arrange
        int vacinacaoId = 1;

        // Act
        ResponseEntity<VacinacaoResponseDTO> response = vacinacaoController.delete(vacinacaoId);

        // Assert
        verify(vacinacaoService, times(1)).delete(vacinacaoId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }
}

