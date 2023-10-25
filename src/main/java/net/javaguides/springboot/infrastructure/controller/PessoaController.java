package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.usecase.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService service;

    @GetMapping("/aprovadas")
    public ResponseEntity<List<Pessoa>> listarPessoasAprovadas() {
        List<Pessoa> pessoasAprovadas = service.listPessoasAprovadas();
        return ResponseEntity.ok(pessoasAprovadas);
    }
}
