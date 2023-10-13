package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.ExameRequestDTO;
import net.javaguides.springboot.domain.dtos.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.usecase.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/exames")
public class ExameController {

    @Autowired
    private ExameService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ExameResponseDTO> findById(@PathVariable Integer id){
        Exame exame = service.findById(id);

        return ResponseEntity.ok().body(new ExameResponseDTO(exame));
    }

    @GetMapping
    public ResponseEntity<List<ExameResponseDTO>> findAll(){
        List<Exame> list = service.findAll();
        List<ExameResponseDTO> listDto = list.stream().map(obj -> new ExameResponseDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/exame/{idPessoa}")
    public ResponseEntity<List<ExameResponseDTO>> findAllExamesPorPessoa(@PathVariable Integer idPessoa){
        List<ExameResponseDTO> list = service.findExamesPorPessoa(idPessoa);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Exame> create(@Valid @RequestBody ExameRequestDTO dto){
        Exame newExame = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newExame.getIdExame()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ExameResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ExameResponseDTO dto){
        Exame campanha = service.update(id, dto);

        return ResponseEntity.ok().body(new ExameResponseDTO(campanha));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ExameResponseDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
