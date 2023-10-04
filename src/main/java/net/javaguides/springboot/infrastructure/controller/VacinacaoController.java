package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.dtos.VacinacaoRequestDTO;
import net.javaguides.springboot.domain.dtos.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.usecase.VacinacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/vacinacoes")
public class VacinacaoController {

    @Autowired
    private VacinacaoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<VacinacaoResponseDTO> findById(@PathVariable Integer id){
        Vacinacao vacinacao = service.findById(id);

        return ResponseEntity.ok().body(new VacinacaoResponseDTO(vacinacao));
    }

    @GetMapping
    public ResponseEntity<List<VacinacaoResponseDTO>> findAll(){
        List<Vacinacao> list = service.findAll();
        List<VacinacaoResponseDTO> listDto = list.stream().map(obj -> new VacinacaoResponseDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/pessoa/{idPessoa}")
    public ResponseEntity<List<VacinacaoResponseDTO>> findAllVacinacoesPorPessoa(@PathVariable Integer idPessoa){
        List<VacinacaoResponseDTO> list = service.findVacinacoesPorPessoa(idPessoa);

        return ResponseEntity.ok().body(list);
    }



    @PostMapping
    public ResponseEntity<List<Vacinacao>> create(@Valid @RequestBody VacinacaoRequestDTO dto){
        List<Vacinacao> vacinacoes = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinacoes);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VacinacaoResponseDTO> update(@PathVariable Integer id, @Valid @RequestBody VacinacaoRequestDTO dto){
        Vacinacao campanha = service.update(id, dto);

        return ResponseEntity.ok().body(new VacinacaoResponseDTO(campanha));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VacinacaoResponseDTO> delete(@PathVariable Integer id){
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
