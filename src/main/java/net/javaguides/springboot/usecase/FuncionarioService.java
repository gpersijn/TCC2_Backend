package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.FuncionarioDTO;
import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Pessoa;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.repository.FuncionarioRepository;
import net.javaguides.springboot.domain.repository.PessoaRepository;
import net.javaguides.springboot.usecase.exceptions.DataViolationException;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Funcionario findById(Integer id){
        Optional<Funcionario> obj = funcionarioRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Funcionario create(FuncionarioDTO dto) {
        dto.setId(null);
        dto.setIsApproved(Boolean.FALSE);
        dto.addPerfil(PerfilEnum.FUNCIONARIO);
        dto.setSenha(encoder.encode(dto.getSenha()));
        validaCpfEmail(dto);
        Funcionario newFuncionario = new Funcionario(dto);
        return funcionarioRepository.save(newFuncionario);
    }

    public Funcionario update(Integer id, FuncionarioDTO dto) {
        dto.setId(id);
        Funcionario oldFuncionario = findById(id);
        validaCpfEmail(dto);

        Funcionario newFuncionario = atualizarValores(dto, oldFuncionario);

        return funcionarioRepository.save(newFuncionario);
    }

    public void delete(Integer id) {
        Funcionario funcionario = findById(id);

        funcionarioRepository.deleteById(id);
    }

    private void validaCpfEmail(FuncionarioDTO dto) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(dto.getCpf());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), dto.getId())){
            throw new DataViolationException("Cpf já cadastrado no sistema!");
        }
        pessoa = pessoaRepository.findByEmail(dto.getEmail());
        if(pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), dto.getId())){
            throw new DataViolationException("Email já cadastrado no sistema!");
        }
    }

    public Funcionario adicionarPerfil(String email, PerfilEnum perfilEnum){
        Funcionario funcionario = findByEmail(email);

        if(funcionario == null)  throw new DataIntegrityViolationException("Usuário não encontrado!");
        if(funcionario.getPerfis().contains(perfilEnum)){
            throw new DataIntegrityViolationException("O usuário já possui permissões do perfil selecionado!");
        }
        funcionario.addPerfil(perfilEnum);

        return funcionarioRepository.save(funcionario);
    }

    public Funcionario findByEmail(String email){
        Optional<Funcionario> obj = funcionarioRepository.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Email: " + email));
    }


    public List<Funcionario> listFuncionariosNaoAprovados() {
        return funcionarioRepository.findByIsApprovedFalse();
    }


    public Funcionario aprovarLogin(Integer id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        if(!funcionario.isPresent())  throw new DataIntegrityViolationException("Usuário não encontrado!");
        if(funcionario.get().getIsApproved()){
            funcionario.get().setIsApproved(Boolean.FALSE);
        } else {
            funcionario.get().setIsApproved(Boolean.TRUE);
        }
        return funcionarioRepository.save(funcionario.get());
    }

    private static Funcionario atualizarValores(FuncionarioDTO dto, Funcionario oldFuncionario) {
        if (dto.getPrimeiroNome() != null) {
            oldFuncionario.setPrimeiroNome(dto.getPrimeiroNome());
        }
        if (dto.getUltimoNome() != null) {
            oldFuncionario.setUltimoNome(dto.getUltimoNome());
        }
        if (dto.getEmail() != null) {
            oldFuncionario.setEmail(dto.getEmail());
        }
        if (dto.getSetor() != null) {
            oldFuncionario.setSetor(dto.getSetor());
        }
        if (dto.getTelefone() != null) {
            oldFuncionario.setTelefone(dto.getTelefone());
        }
        if (dto.getDataAniversario() != null) {
            oldFuncionario.setDataAniversario(dto.getDataAniversario());
        }
        if (dto.getSexoEnum() != null) {
            oldFuncionario.setSexoEnum(dto.getSexoEnum());
        }
        if (dto.getCpf() != null) {
            oldFuncionario.setCpf(dto.getCpf());
        }
        return oldFuncionario;
    }
}
