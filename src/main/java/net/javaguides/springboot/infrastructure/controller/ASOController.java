package net.javaguides.springboot.infrastructure.controller;


import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.dtos.response.ASOResponseDTO;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.usecase.ASOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/aso")
public class ASOController {

    @Autowired
    private ASOService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ASOResponseDTO> findById(@PathVariable Integer id){
        ASO aso = service.findById(id);

        return ResponseEntity.ok().body(new ASOResponseDTO(aso));
    }

    @GetMapping
    public ResponseEntity<List<ASOResponseDTO>> findAll(){
        List<ASO> list = service.findAll();
        List<ASOResponseDTO> listDto = list.stream().map(obj -> new ASOResponseDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/pessoa/{idPessoa}")
    public ResponseEntity<List<ASOResponseDTO>> findAllASOsPorPessoa(@PathVariable Integer idPessoa){
        List<ASOResponseDTO> list = service.findASOPorPessoa(idPessoa);

        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ASO> create(@Valid @RequestBody ASORequestDTO dto){
        ASO aso = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aso);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ASOResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody ASORequestDTO dto){
        ASO aso = service.update(id, dto);

        return ResponseEntity.ok().body(new ASOResponseDTO(aso));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ASOResponseDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
