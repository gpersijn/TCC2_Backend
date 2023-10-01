package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.VacinacaoRequestDTO;
import net.javaguides.springboot.domain.dtos.VacinacaoResponseDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.entity.Vacinacao;
import net.javaguides.springboot.domain.enums.StatusVacinacaoEnum;
import net.javaguides.springboot.domain.repository.CampanhaRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.domain.repository.VacinacaoRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VacinacaoService {

    @Autowired
    private VacinacaoRepository vacinacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;

    public Vacinacao findById(Integer id){
        Optional<Vacinacao> obj = vacinacaoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<Vacinacao> findAll() {
        return vacinacaoRepository.findAll();
    }

    public List<Vacinacao> create(VacinacaoRequestDTO dto) {
        dto.setId(null);
        List<Vacinacao> vacinacoes = new ArrayList<>();

        Campanha campanha = campanhaRepository.findById(dto.getIdCampanha()).orElseThrow(() -> new EntityNotFoundException("Campanha não encontrada com o ID fornecido"));

        try{
            List<Pessoa> listaPessoas = pessoaRepository.findAllById(dto.getIdFuncionarios());
            for(Pessoa pessoa : listaPessoas){
                Vacinacao newVacinacao = new Vacinacao(dto.getId(), StatusVacinacaoEnum.PENDENTE, campanha, pessoa);
                vacinacoes.add(newVacinacao);
            }
        } catch (Exception e){
            throw new EntityNotFoundException("Algum funcionario indicado nao existe");
        }

        return vacinacaoRepository.saveAll(vacinacoes);
    }

    public Vacinacao update(Integer id, VacinacaoRequestDTO dto) {
        dto.setId(id);
        Vacinacao oldVacinacao = findById(id);
        Vacinacao newVacinacao = atualizarValores(dto, oldVacinacao);

        return vacinacaoRepository.save(newVacinacao);
    }

    private Vacinacao atualizarValores(VacinacaoRequestDTO dto, Vacinacao oldVacinacao) {
        if (dto.getStatus() != null) {
            oldVacinacao.setStatus(dto.getStatus());
        }
        return oldVacinacao;
    }

    public void delete(Integer id) {
        campanhaRepository.deleteById(id);
    }
}
