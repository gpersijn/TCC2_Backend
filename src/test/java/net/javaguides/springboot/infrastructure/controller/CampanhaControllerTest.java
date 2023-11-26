package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.CampanhaDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.mocks.CampanhaDTOMock;
import net.javaguides.springboot.usecase.CampanhaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CampanhaControllerTest {

    @InjectMocks
    private CampanhaController campanhaController;

    @Mock
    private CampanhaService campanhaService;

    @Test
    void findById() {
        // Arrange
        int campanhaId = 1;
        Campanha campanha = new Campanha();
        when(campanhaService.findById(campanhaId)).thenReturn(campanha);

        // Act
        ResponseEntity<CampanhaDTO> response = campanhaController.findById(campanhaId);

        // Assert
        verify(campanhaService, times(1)).findById(campanhaId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findAll() {
        // Arrange
        Campanha campanha1 = new Campanha();
        Campanha campanha2 = new Campanha();
        List<Campanha> campanhaList = Arrays.asList(campanha1, campanha2);
        when(campanhaService.findAll()).thenReturn(campanhaList);

        // Act
        ResponseEntity<List<CampanhaDTO>> response = campanhaController.findAll();

        // Assert
        verify(campanhaService, times(1)).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        // Arrange
        CampanhaDTO campanhaDTO = CampanhaDTOMock.withDefaultValues();
        Campanha newCampanha = new Campanha();
        when(campanhaService.create(campanhaDTO)).thenReturn(newCampanha);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ResponseEntity<CampanhaDTO> response = campanhaController.create(campanhaDTO);

        // Assert
        verify(campanhaService, times(1)).create(campanhaDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void update() {
        // Arrange
        int campanhaId = 1;
        CampanhaDTO campanhaDTO = new CampanhaDTO();
        Campanha updatedCampanha = new Campanha();
        when(campanhaService.update(campanhaId, campanhaDTO)).thenReturn(updatedCampanha);

        // Act
        ResponseEntity<CampanhaDTO> response = campanhaController.update(campanhaId, campanhaDTO);

        // Assert
        verify(campanhaService, times(1)).update(campanhaId, campanhaDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete() {
        // Arrange
        int campanhaId = 1;

        // Act
        ResponseEntity<CampanhaDTO> response = campanhaController.delete(campanhaId);

        // Assert
        verify(campanhaService, times(1)).delete(campanhaId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
