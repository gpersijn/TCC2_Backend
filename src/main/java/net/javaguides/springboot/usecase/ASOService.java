package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.request.ASORequestDTO;
import net.javaguides.springboot.domain.dtos.response.ASOResponseDTO;
import net.javaguides.springboot.domain.entity.ASO;
import net.javaguides.springboot.domain.entity.Exame;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.repository.ASORepository;
import net.javaguides.springboot.domain.repository.ExameRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ASOService {

    @Autowired
    private ASORepository asoRepository;

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PessoaRepository pessoaRepository;


    public ASO findById(Integer id){
        Optional<ASO> obj = asoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<ASO> findAll() {
        return asoRepository.findAll();
    }

    public ASO create(ASORequestDTO dto) {
        dto.setIdASO(null);
        List<Exame> listaExames = exameRepository.findAllById(dto.getExames());
        Optional<Pessoa> findPessoa = pessoaRepository.findById(dto.getIdPessoa());
        Pessoa pessoa = findPessoa.orElse(null);

        ASO newAso = new ASO(dto, listaExames, pessoa);

        return asoRepository.save(newAso);
    }

    public ASO update(Integer id, ASORequestDTO dto) {
        ASO oldASO = findById(id);
        Optional<Pessoa> findPessoa = pessoaRepository.findById(dto.getIdPessoa());
        Pessoa pessoa = findPessoa.orElse(null);

        ASO newASO = atualizarValores(dto, oldASO, pessoa);

        return asoRepository.save(newASO);
    }

    private ASO atualizarValores(ASORequestDTO dto, ASO oldASO, Pessoa pessoa) {
        if (dto.getCnpj() != null) {
            oldASO.setCnpj(dto.getCnpj());
        }
        if (dto.getNomeEmpresa() != null) {
            oldASO.setNomeEmpresa(dto.getNomeEmpresa());
        }
        if (dto.getIdPessoa() != null) {
            oldASO.setPessoa(pessoa);
        }
        if (dto.getRisco() != null) {
            oldASO.setRisco(dto.getRisco());
        }
        if (dto.getExames() != null) {
            List<Exame> listaExames = exameRepository.findAllById(dto.getExames());
            oldASO.setExames(listaExames);
        }
        if (dto.getNomeMedicoPCMSO() != null) {
            oldASO.setNomeMedicoPCMSO(dto.getNomeMedicoPCMSO());
        }
        if (dto.getCrmMedicoPCMSO() != null) {
            oldASO.setCrmMedicoPCMSO(dto.getCrmMedicoPCMSO());
        }
        if (dto.getNomeMedicoClinico() != null) {
            oldASO.setNomeMedicoClinico(dto.getNomeMedicoClinico());
        }
        if (dto.getCrmMedicoClinico() != null) {
            oldASO.setCrmMedicoClinico(dto.getCrmMedicoClinico());
        }
        if (dto.getResultadoASO() != null) {
            oldASO.setResultadoASO(dto.getResultadoASO());
        }
        if (dto.getValidade() != null) {
            oldASO.setValidade(dto.getValidade());
        }
        if (dto.getDataASO() != null) {
            oldASO.setDataASO(dto.getDataASO());
        }
        return oldASO;
    }

    public void delete(Integer id) {
        asoRepository.deleteById(id);
    }

    public List<ASOResponseDTO> findASOPorPessoa(Integer idPessoa){
        return asoRepository.findByPessoaId(idPessoa);
    }

    public Long countAptoASOs() {
        return asoRepository.countAptoASOs();
    }

    public Long countInaptoASOs() {
        return asoRepository.countInaptoASOs();
    }
}
