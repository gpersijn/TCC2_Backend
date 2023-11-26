package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.usecase.PessoaService;
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

public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListarPessoasAprovadas() {
        // Arrange
        List<Pessoa> pessoasAprovadas = new ArrayList<>();
        when(pessoaService.listPessoasAprovadas()).thenReturn(pessoasAprovadas);

        // Act
        ResponseEntity<List<Pessoa>> response = pessoaController.listarPessoasAprovadas();

        // Assert
        verify(pessoaService, times(1)).listPessoasAprovadas();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoasAprovadas, response.getBody());
    }
}
