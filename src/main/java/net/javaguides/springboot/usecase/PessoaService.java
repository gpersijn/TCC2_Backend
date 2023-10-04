package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listPessoasAprovadas() {
        return pessoaRepository.findByIsApprovedTrue();
    }
}
