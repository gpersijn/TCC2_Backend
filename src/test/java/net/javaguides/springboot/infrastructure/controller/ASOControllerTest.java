package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.dtos.response.ASOResponseDTO;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.usecase.ASOService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ASOControllerTest {

    @InjectMocks
    private ASOController asoController;

    @Mock
    private ASOService asoService;

    @Test
    void findById() {
        // Arrange
        int asoId = 1;
        ASO aso = new ASO();
        when(asoService.findById(asoId)).thenReturn(aso);

        // Act
        ResponseEntity<ASOResponseDTO> response = asoController.findById(asoId);

        // Assert
        verify(asoService, times(1)).findById(asoId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findAll() {
        // Arrange
        ASO aso1 = new ASO();
        ASO aso2 = new ASO();
        List<ASO> asoList = Arrays.asList(aso1, aso2);
        when(asoService.findAll()).thenReturn(asoList);

        // Act
        ResponseEntity<List<ASOResponseDTO>> response = asoController.findAll();

        // Assert
        verify(asoService, times(1)).findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findAllASOsPorPessoa() {
        // Arrange
        int idPessoa = 1;
        ASO aso1 = new ASO();
        ASO aso2 = new ASO();
        List<ASOResponseDTO> asoResponseDTOList = Arrays.asList(new ASOResponseDTO(aso1), new ASOResponseDTO(aso2));
        when(asoService.findASOPorPessoa(idPessoa)).thenReturn(asoResponseDTOList);

        // Act
        ResponseEntity<List<ASOResponseDTO>> response = asoController.findAllASOsPorPessoa(idPessoa);

        // Assert
        verify(asoService, times(1)).findASOPorPessoa(idPessoa);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void create() {
        // Arrange
        ASORequestDTO asoRequestDTO = new ASORequestDTO();
        ASO newAso = new ASO();
        when(asoService.create(asoRequestDTO)).thenReturn(newAso);

        // Act
        ResponseEntity<ASO> response = asoController.create(asoRequestDTO);

        // Assert
        verify(asoService, times(1)).create(asoRequestDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void update() {
        // Arrange
        int asoId = 1;
        ASORequestDTO asoRequestDTO = new ASORequestDTO();
        ASO updatedAso = new ASO();
        when(asoService.update(asoId, asoRequestDTO)).thenReturn(updatedAso);

        // Act
        ResponseEntity<ASOResponseDTO> response = asoController.update(asoId, asoRequestDTO);

        // Assert
        verify(asoService, times(1)).update(asoId, asoRequestDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void delete() {
        // Arrange
        int asoId = 1;

        // Act
        ResponseEntity<ASOResponseDTO> response = asoController.delete(asoId);

        // Assert
        verify(asoService, times(1)).delete(asoId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}

