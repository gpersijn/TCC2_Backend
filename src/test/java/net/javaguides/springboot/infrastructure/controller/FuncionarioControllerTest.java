package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.Mock.FuncionarioMock;
import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.dtos.PerfilDTO;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.usecase.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FuncionarioControllerTest {

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        // Arrange
        int funcionarioId = 1;
        Funcionario funcionario = new Funcionario(); // Preencha com os dados do funcionário
        when(funcionarioService.findById(funcionarioId)).thenReturn(funcionario);

        // Act
        ResponseEntity<FuncionarioDTO> response = funcionarioController.findById(funcionarioId);

        // Assert
        verify(funcionarioService, times(1)).findById(funcionarioId);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testFindAll() {
        // Arrange
        List<Funcionario> funcionarios = new ArrayList<>(); // Preencha com os dados dos funcionários
        when(funcionarioService.findAll()).thenReturn(funcionarios);

        // Act
        ResponseEntity<List<FuncionarioDTO>> response = funcionarioController.findAll();

        // Assert
        verify(funcionarioService, times(1)).findAll();
        assert response.getStatusCode() == HttpStatus.OK;
    }

//    @Test
//    public void testCreate() {
//        // Arrange
//        FuncionarioDTO funcionarioDTO = FuncionarioMock.valorPadraoDto();
//        Funcionario novoFuncionario = new Funcionario(); // Preencha com os dados do novo funcionário
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoFuncionario.getId()).toUri();
//        when(funcionarioService.create(funcionarioDTO)).thenReturn(novoFuncionario);
//
//        // Act
//        ResponseEntity<FuncionarioDTO> response = funcionarioController.create(funcionarioDTO);
//
//        // Assert
//        verify(funcionarioService, times(1)).create(funcionarioDTO);
//        assert response.getStatusCode() == HttpStatus.CREATED;
//        assert response.getHeaders().getLocation().equals(uri);
//    }

    @Test
    public void testUpdate() {
        // Arrange
        int funcionarioId = 1;
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(); // Preencha com os dados do DTO
        Funcionario funcionario = new Funcionario(); // Preencha com os dados do funcionário atualizado
        when(funcionarioService.update(funcionarioId, funcionarioDTO)).thenReturn(funcionario);

        // Act
        ResponseEntity<FuncionarioDTO> response = funcionarioController.update(funcionarioId, funcionarioDTO);

        // Assert
        verify(funcionarioService, times(1)).update(funcionarioId, funcionarioDTO);
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testDelete() {
        // Arrange
        int funcionarioId = 1;

        // Act
        ResponseEntity<FuncionarioDTO> response = funcionarioController.delete(funcionarioId);

        // Assert
        verify(funcionarioService, times(1)).delete(funcionarioId);
        assert response.getStatusCode() == HttpStatus.NO_CONTENT;
    }

    @Test
    public void testAddPerfil() {
        // Arrange
        PerfilDTO perfilDTO = new PerfilDTO(); // Preencha com os dados do perfil
        Funcionario funcionario = new Funcionario(); // Preencha com os dados do funcionário
        when(funcionarioService.adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum())).thenReturn(funcionario);

        // Act
        ResponseEntity<FuncionarioDTO> response = funcionarioController.addPerfil(perfilDTO);

        // Assert
        verify(funcionarioService, times(1)).adicionarPerfil(perfilDTO.getEmail(), perfilDTO.getPerfilEnum());
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testListarTecnicosNaoAprovados() {
        // Arrange
        List<Funcionario> tecnicosNaoAprovados = new ArrayList<>(); // Preencha com os dados dos técnicos não aprovados
        when(funcionarioService.listFuncionariosNaoAprovados()).thenReturn(tecnicosNaoAprovados);

        // Act
        ResponseEntity<List<FuncionarioDTO>> response = funcionarioController.listarTecnicosNaoAprovados();

        // Assert
        verify(funcionarioService, times(1)).listFuncionariosNaoAprovados();
        assert response.getStatusCode() == HttpStatus.OK;
    }

    @Test
    public void testAprovar() {
        // Arrange
        int funcionarioId = 1;
        Funcionario funcionario = new Funcionario(); // Preencha com os dados do funcionário aprovado
        when(funcionarioService.aprovarLogin(funcionarioId)).thenReturn(funcionario);

        // Act
        ResponseEntity<FuncionarioDTO> response = funcionarioController.aprovar(funcionarioId);

        // Assert
        verify(funcionarioService, times(1)).aprovarLogin(funcionarioId);
        assert response.getStatusCode() == HttpStatus.OK;
    }
}