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

    public Tecnico create(TecnicoDTO dto) {
        dto.setId(null);
        dto.setSenha(encoder.encode(dto.getSenha()));
        validaCpfEmail(dto);
        Tecnico newTecnico = new Tecnico(dto);
        return tecnicoRepository.save(newTecnico);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO dto) {
        dto.setId(id);
        Tecnico oldTecnico = findById(id);
        validaCpfEmail(dto);
        oldTecnico = new Tecnico(dto);

        return tecnicoRepository.save(oldTecnico);

    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if(!tecnico.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("Tecnico possui ordens de serviço e não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    private void validaCpfEmail(TecnicoDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if(pessoa.isPresent() && pessoa.get().getId() != dto.getId()){
            throw new DataViolationException("Cpf já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        if(pessoa.isPresent() && pessoa.get().getId() != dto.getId()){
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
