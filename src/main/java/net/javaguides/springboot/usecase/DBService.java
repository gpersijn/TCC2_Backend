package net.javaguides.springboot.usecase;

import net.javaguides.springboot.domain.entity.Funcionario;
import net.javaguides.springboot.domain.entity.Tecnico;
import net.javaguides.springboot.domain.enums.PerfilEnum;
import net.javaguides.springboot.domain.enums.SexoEnum;
import net.javaguides.springboot.domain.repository.FuncionarioRepository;
import net.javaguides.springboot.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;


    public void instanciaDB() {

//        Tecnico tec1 = new Tecnico(
//                null,
//                "Banca",
//                "TCC",
//                "tecnico@gmail.com",
//                encoder.encode("tecnico"),
//                "61999998888",
//                LocalDate.of(2001, 5, 30),
//                SexoEnum.FEMININO,
//                "05664830174");
//
//        tec1.addPerfil(PerfilEnum.ADMIN);
//        tec1.setIsApproved(Boolean.TRUE);
//
//        Tecnico tec2 = new Tecnico(null,
//                "Fulano",
//                "Da Silva",
//                "teste@gmail.com",
//                encoder.encode("teste"),
//                "61999998888",
//                LocalDate.of(2001, 5, 30),
//                SexoEnum.MASCULINO,
//                "53891687044");
//        tec2.setIsApproved(false);
//
//        Tecnico tec3 = new Tecnico(
//                null,
//                "Maria Oliveira",
//                "Analista de Sistemas",
//                "maria.oliveira@gmail.com",
//                encoder.encode("senha"),
//                "555-123-4567",
//                LocalDate.of(1990, 7, 22),
//                SexoEnum.FEMININO,
//                "42053292024");
//        tec3.setIsApproved(Boolean.FALSE);
//
//        Tecnico tec4 = new Tecnico(
//                null,
//                "Carlos Pereira",
//                "Médico",
//                "carlos.pereira@gmail.com",
//                encoder.encode("senha"),
//                "555-999-0000",
//                LocalDate.of(1975, 3, 10),
//                SexoEnum.MASCULINO,
//                "65882395054");
//        tec4.setIsApproved(Boolean.FALSE);
//
//
//        Funcionario func1 = new Funcionario(null,
//                "Banca",
//                "de TCC",
//                "funcionario@gmail.com",
//                encoder.encode("funcionario"),
//                "61999998889",
//                LocalDate.of(2001, 5, 30),
//                SexoEnum.MASCULINO,
//                "08987761045");
//        func1.setIsApproved(Boolean.FALSE);
//
//        Funcionario func2 = new Funcionario(null,
//                "Ciclano",
//                "Gomes",
//                "teste@hotmail.com",
//                encoder.encode("teste"),
//                "61999998889",
//                LocalDate.of(2001, 5, 30),
//                SexoEnum.MASCULINO,
//                "17039470020");
//        func2.setIsApproved(Boolean.TRUE);
//
//        Funcionario func3 = new Funcionario(
//                null,
//                "Rafael Oliveira",
//                "Analista de Marketing",
//                "rafael.oliveira@gmail.com",
//                encoder.encode("senha"),
//                "555-123-7890",
//                LocalDate.of(1995, 8, 8),
//                SexoEnum.MASCULINO,
//                "05318257011");
//        func3.setIsApproved(Boolean.FALSE);
//
//        Funcionario func4 = new Funcionario(
//                null,
//                "Mariana Santos",
//                "Engenheira Civil",
//                "mariana.santos@gmail.com",
//                encoder.encode("senha789"),
//                "555-999-0000",
//                LocalDate.of(1988, 4, 20),
//                SexoEnum.FEMININO,
//                "44423381087");
//        func4.setIsApproved(Boolean.FALSE);
//
//        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4));
//        funcionarioRepository.saveAll(Arrays.asList(func1, func2, func3, func4));
    }

}
