package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.CampanhaDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.usecase.CampanhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/campanhas")
public class CampanhaController {

    @Autowired
    private CampanhaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CampanhaDTO> findById(@PathVariable Integer id){
        Campanha campanha = service.findById(id);

        return ResponseEntity.ok().body(new CampanhaDTO(campanha));
    }

    @GetMapping
    public ResponseEntity<List<CampanhaDTO>> findAll(){
        List<Campanha> list = service.findAll();
        List<CampanhaDTO> listDto = list.stream().map(obj -> new CampanhaDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<CampanhaDTO> create(@Valid @RequestBody CampanhaDTO dto){
        Campanha newCampanha = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCampanha.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CampanhaDTO> update(@PathVariable Integer id, @Valid @RequestBody CampanhaDTO dto){
        Campanha campanha = service.update(id, dto);

        return ResponseEntity.ok().body(new CampanhaDTO(campanha));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CampanhaDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
