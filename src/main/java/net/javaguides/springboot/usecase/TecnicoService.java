package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.TecnicoDTO;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.domain.repository.TecnicoRepository;
import net.javaguides.springboot.usecase.exceptions.DataViolationException;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService{

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(@Valid TecnicoDTO dto) {
        dto.setIdTecnico(null);
        dto.setIsApproved(Boolean.FALSE);
        dto.setSenha(encoder.encode(dto.getSenha()));
        dto.addPerfil(PerfilEnum.TECNICO);
        validaCpfEmail(dto);
        Tecnico newTecnico = new Tecnico(dto);
        return tecnicoRepository.save(newTecnico);
    }

    public Tecnico update(Integer id, TecnicoDTO dto) {
        dto.setIdTecnico(id);
        Tecnico oldTecnico = findById(id);

        validaCpfEmail(dto);

        Tecnico newTecnico = atualizarValores(dto, oldTecnico);

        return tecnicoRepository.save(newTecnico);
    }

    protected static Tecnico atualizarValores(TecnicoDTO dto, Tecnico oldTecnico) {
        if (dto.getPrimeiroNome() != null) {
            oldTecnico.setPrimeiroNome(dto.getPrimeiroNome());
        }
        if (dto.getUltimoNome() != null) {
            oldTecnico.setUltimoNome(dto.getUltimoNome());
        }
        if (dto.getEmail() != null) {
            oldTecnico.setEmail(dto.getEmail());
        }
        if (dto.getSetor() != null) {
            oldTecnico.setSetor(dto.getSetor());
        }
        if (dto.getTelefone() != null) {
            oldTecnico.setTelefone(dto.getTelefone());
        }
        if (dto.getDataAniversario() != null) {
            oldTecnico.setDataAniversario(dto.getDataAniversario());
        }
        if (dto.getSexoEnum() != null) {
            oldTecnico.setSexoEnum(dto.getSexoEnum());
        }
        if (dto.getCpf() != null) {
            oldTecnico.setCpf(dto.getCpf());
        }
        return oldTecnico;
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);

        tecnicoRepository.deleteById(id);
    }

    public void validaCpfEmail(TecnicoDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if(pessoa.isPresent() && pessoa.get().getId() != dto.getIdTecnico()){
            throw new DataViolationException("Cpf já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        if(pessoa.isPresent() && pessoa.get().getId() != dto.getIdTecnico()){
            throw new DataViolationException("Email já cadastrado no sistema!");
        }
    }

    public Tecnico adicionarPerfil(String email, PerfilEnum perfilEnum){
        Tecnico tecnico = findByEmail(email);

        if(tecnico == null)  throw new DataIntegrityViolationException("Usuário não encontrado!");
        if(tecnico.getPerfis().contains(perfilEnum)){
            throw new DataIntegrityViolationException("O usuário já possui permissões do perfil selecionado!");
        }
        tecnico.addPerfil(perfilEnum);

        return tecnicoRepository.save(tecnico);
    }

    public Tecnico findByEmail(String email){
        Optional<Tecnico> obj = tecnicoRepository.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Email: " + email));
    }

    public List<Tecnico> listTecnicosNaoAprovados() {
        return tecnicoRepository.findByIsApprovedFalse();
    }

    public Tecnico aprovarLogin(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        if(!tecnico.isPresent())  throw new DataIntegrityViolationException("Usuário não encontrado!");
        if(tecnico.get().getIsApproved()){
            tecnico.get().setIsApproved(Boolean.FALSE);
        } else {
            tecnico.get().setIsApproved(Boolean.TRUE);
        }
        return tecnicoRepository.save(tecnico.get());
    }
}
