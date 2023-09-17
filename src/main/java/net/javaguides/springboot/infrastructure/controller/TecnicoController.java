package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.PerfilDTO;
import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.usecase.TecnicoService;
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
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
        Tecnico tecnico = service.findById(id);

        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = service.findAll();
        List<TecnicoDTO> listDto = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO dto){
        Tecnico newTecnico = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newTecnico.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO dto){
        Tecnico tecnico = service.update(id, dto);

        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/adicionarPerfil")
    public ResponseEntity<TecnicoDTO> addPerfil(@RequestBody PerfilDTO dto){

        Tecnico tecnico = service.adicionarPerfil(dto.getEmail(), dto.getPerfilEnum());

        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping("/naoAprovados")
    public ResponseEntity<List<Tecnico>> listarTecnicosNaoAprovados() {
        List<Tecnico> tecnicosNaoAprovados = service.listTecnicosNaoAprovados();
        return ResponseEntity.ok(tecnicosNaoAprovados);
    }

    @PutMapping(value = "/aprovarTecnico/{id}")
    public ResponseEntity<TecnicoDTO> aprovar(@PathVariable Integer id){
        Tecnico tecnico = service.aprovarLogin(id);

        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

}
