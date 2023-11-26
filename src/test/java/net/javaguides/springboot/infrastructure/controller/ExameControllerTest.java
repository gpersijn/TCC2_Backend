package net.javaguides.springboot.infrastructure.controller;


import net.javaguides.springboot.domain.dtos.request.ExameRequestDTO;
import net.javaguides.springboot.domain.dtos.response.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.mocks.ExameDTOMock;
import net.javaguides.springboot.usecase.ExameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ExameControllerTest {

    @InjectMocks
    private ExameController exameController;

    @Mock
    private ExameService exameService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindById() {
        // Arrange
        int exameId = 1;
        Exame exame = new Exame();
        when(exameService.findById(exameId)).thenReturn(exame);

        // Act
        ResponseEntity<ExameResponseDTO> response = exameController.findById(exameId);

        // Assert
        verify(exameService, times(1)).findById(exameId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testFindAll() {
        // Arrange
        List<Exame> exames = new ArrayList<>();
        when(exameService.findAll()).thenReturn(exames);

        // Act
        ResponseEntity<List<ExameResponseDTO>> response = exameController.findAll();

        // Assert
        verify(exameService, times(1)).findAll();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testCreate() {
        // Arrange
        ExameRequestDTO exameDTO = ExameDTOMock.withRequestValues();
        Exame newExame = new Exame();

        when(exameService.create(exameDTO)).thenReturn(newExame);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        ResponseEntity<Exame> response = exameController.create(exameDTO);

        // Assert
        verify(exameService, times(1)).create(exameDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    void testUpdate() {
        // Arrange
        int exameId = 1;
        ExameResponseDTO exameResponseDTO = new ExameResponseDTO();
        Exame exame = new Exame();
        when(exameService.update(exameId, exameResponseDTO)).thenReturn(exame);

        // Act
        ResponseEntity<ExameResponseDTO> response = exameController.update(exameId, exameResponseDTO);

        // Assert
        verify(exameService, times(1)).update(exameId, exameResponseDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testDelete() {
        // Arrange
        int exameId = 1;

        // Act
        ResponseEntity<ExameResponseDTO> response = exameController.delete(exameId);

        // Assert
        verify(exameService, times(1)).delete(exameId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}