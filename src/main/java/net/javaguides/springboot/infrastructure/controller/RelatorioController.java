package net.javaguides.springboot.infrastructure.controller;

import net.javaguides.springboot.usecase.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService service;

    @GetMapping("/grafico-pizza/quantidade-por-sexo")
    public Map<String, Object> obterDadosSexo() {
        return service.getDadosSexo();
    }

    @GetMapping("/grafico-pizza/quantidade-por-setor")
    public Object[] obterDadosSetor() {
        return service.getDadosSetor();
    }

    @GetMapping("/grafico-pizza/quantidade-por-aptidao")
    public Map<String, Object> obterDadosAptidao() {
        return service.getDadosAptidao();
    }

    @GetMapping("/grafico-torre/quantidade-por-campanha")
    public Object[] obterDadosCampanha() {
        return service.getDadosCampanha();
    }

    @GetMapping("/grafico-pizza/quantidade-por-status-campanha/{idCampanha}")
    public Object[] obterDadosStatusCampanha(@PathVariable Integer idCampanha) {
        return service.getDadosStatusCampanha(idCampanha);
    }


}
