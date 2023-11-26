package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.usecase.RelatorioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RelatorioControllerTest {

    @InjectMocks
    private RelatorioController relatorioController;

    @Mock
    private RelatorioService relatorioService;

    @Test
    void obterDadosSexo() {
        // Arrange
        when(relatorioService.getDadosSexo()).thenReturn(mock(Map.class));

        // Act
        Map<String, Object> result = relatorioController.obterDadosSexo();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosSexo();
    }

    @Test
    void obterDadosSetor() {
        Object[] object = new Object[0];
        // Arrange
        when(relatorioService.getDadosSetor()).thenReturn(object);

        // Act
        Object result = relatorioController.obterDadosSetor();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosSetor();
    }

    @Test
    void obterDadosAptidao() {
        // Arrange
        when(relatorioService.getDadosAptidao()).thenReturn(mock(Map.class));

        // Act
        Map<String, Object> result = relatorioController.obterDadosAptidao();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosAptidao();
    }

    @Test
    void obterDadosCampanha() {
        Object[] object = new Object[0];
        // Arrange
        when(relatorioService.getDadosCampanha()).thenReturn(object);

        // Act
        Object[] result = relatorioController.obterDadosCampanha();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosCampanha();
    }

    @Test
    void obterDadosStatusCampanha() {
        Object[] object = new Object[0];
        // Arrange
        Integer idCampanha = 1;
        when(relatorioService.getDadosStatusCampanha(idCampanha)).thenReturn(object);

        // Act
        Object[] result = relatorioController.obterDadosStatusCampanha(idCampanha);

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosStatusCampanha(idCampanha);
    }

    @Test
    void obterDadosExames() {
        Object[] object = new Object[0];
        // Arrange
        when(relatorioService.getDadosExames()).thenReturn(object);

        // Act
        Object[] result = relatorioController.obterDadosExames();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosExames();
    }

    @Test
    void obterDadosStatusExames() {
        Object[] object = new Object[0];
        // Arrange
        when(relatorioService.getDadosStatusExames()).thenReturn(object);

        // Act
        Object[] result = relatorioController.obterDadosStatusExames();

        // Assert
        assertNotNull(result);
        verify(relatorioService, times(1)).getDadosStatusExames();
    }
}


