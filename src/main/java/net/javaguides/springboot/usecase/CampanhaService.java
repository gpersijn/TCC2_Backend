package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.dtos.CampanhaDTO;
import net.javaguides.springboot.domain.entity.Campanha;
import net.javaguides.springboot.domain.repository.CampanhaRepository;
import net.javaguides.springboot.usecase.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    public Campanha findById(Integer id){
        Optional<Campanha> obj = campanhaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado para o Id: " + id));
    }

    public List<Campanha> findAll() {
        return campanhaRepository.findAll();
    }

    public Campanha create(CampanhaDTO dto) {
        dto.setIdCampanha(null);

        Campanha newCampanha = new Campanha(dto);
        return campanhaRepository.save(newCampanha);
    }

    public Campanha update(Integer id, CampanhaDTO dto) {
        dto.setIdCampanha(id);
        Campanha oldCampanha = findById(id);

        Campanha newCampanha = atualizarValores(dto, oldCampanha);

        return campanhaRepository.save(newCampanha);
    }

    private Campanha atualizarValores(CampanhaDTO dto, Campanha oldCampanha) {
        if (dto.getNomeCampanha() != null) {
            oldCampanha.setNomeCampanha(dto.getNomeCampanha());
        }
        if (dto.getNomeVacina() != null) {
            oldCampanha.setNomeVacina(dto.getNomeVacina());
        }
        if (dto.getDescricao() != null) {
            oldCampanha.setDescricao(dto.getDescricao());
        }
        if (dto.getDataCampanha() != null) {
            oldCampanha.setDataCampanha(dto.getDataCampanha());
        }
        return oldCampanha;
    }

    public void delete(Integer id) {
        campanhaRepository.deleteById(id);
    }

}
