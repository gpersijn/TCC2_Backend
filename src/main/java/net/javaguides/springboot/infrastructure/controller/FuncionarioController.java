package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.dtos.PerfilDTO;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.usecase.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Integer id){
        Funcionario funcionario = service.findById(id);

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll(){
        List<Funcionario> list = service.findAll();
        List<FuncionarioDTO> listDto = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> create(@Valid @RequestBody FuncionarioDTO dto){
        Funcionario newFuncionario = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newFuncionario.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Integer id, @Valid @RequestBody FuncionarioDTO dto){
        Funcionario funcionario = service.update(id, dto);

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<FuncionarioDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/adicionarPerfil")
    public ResponseEntity<FuncionarioDTO> addPerfil(@RequestBody PerfilDTO dto){

        Funcionario funcionario = service.adicionarPerfil(dto.getEmail(), dto.getPerfilEnum());

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

    @GetMapping("/naoAprovados")
    public ResponseEntity<List<FuncionarioDTO>> listarTecnicosNaoAprovados() {
        List<Funcionario> tecnicosNaoAprovados = service.listFuncionariosNaoAprovados();

        List<FuncionarioDTO> listDto = tecnicosNaoAprovados.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok(listDto);
    }

    @PutMapping(value = "/aprovarFuncionario/{id}")
    public ResponseEntity<FuncionarioDTO> aprovar(@PathVariable Integer id){
        Funcionario funcionario = service.aprovarLogin(id);

        return ResponseEntity.ok().body(new FuncionarioDTO(funcionario));
    }

}
