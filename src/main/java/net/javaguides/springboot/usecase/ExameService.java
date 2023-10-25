package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.request.ExameRequestDTO;
import net.javaguides.springboot.domain.dtos.response.ExameResponseDTO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.repository.ExameRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Exame findById(Integer id){
        Optional<Exame> obj = exameRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<Exame> findAll() {
        return exameRepository.findAll();
    }

    public Exame create(ExameRequestDTO dto) {
        dto.setIdExame(null);
        Optional<Pessoa> findPessoa = pessoaRepository.findById(dto.getIdPessoa());
        Pessoa pessoa = findPessoa.orElse(null);

        Exame newExame = new Exame(dto, pessoa);
        newExame.setStatusExame(StatusExameEnum.PENDENTE);
        return exameRepository.save(newExame);
    }

    public Exame update(Integer id, ExameResponseDTO dto) {
        dto.setIdExame(id);
        Exame oldExame = findById(id);

        Exame newExame = atualizarValores(dto, oldExame);

        return exameRepository.save(newExame);
    }

    private Exame atualizarValores(ExameResponseDTO dto, Exame oldExame) {
        if (dto.getStatusExame() != null) {
            oldExame.setStatusExame(dto.getStatusExame());
        }
        if(dto.getHoraExame() != null) {
            oldExame.setHoraExame(dto.getHoraExame());
        }
        if(dto.getDataExame() != null) {
            oldExame.setDataExame(dto.getDataExame());
        }
        if(dto.getNomeExame() != null) {
            oldExame.setNomeExame(dto.getNomeExame());
        }
        if (dto.getLocalExame() != null){
            oldExame.setLocalExame(dto.getLocalExame());
        }

        return oldExame;
    }

    public void delete(Integer id) {
        exameRepository.deleteById(id);
    }

    public List<ExameResponseDTO> findExamesPorPessoa(Integer idPessoa) {
        return exameRepository.findByPessoaId(idPessoa);
    }
}
