package net.javaguides.springboot.infrastructure.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.springboot.domain.dtos.request.ExameRequestDTO;
import net.javaguides.springboot.domain.enums.StatusExameEnum;
import net.javaguides.springboot.domain.enums.TipoExameEnum;
import net.javaguides.springboot.usecase.ExameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;


@AutoConfigureMockMvc
@SpringBootTest
public class ExameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired ExameService exameService;

    @Test
    public void testPostExame() throws Exception {

        ExameRequestDTO exameRequestDTO = new ExameRequestDTO();
        exameRequestDTO.setDataExame(LocalDate.of(2020, 5, 30));
        exameRequestDTO.setHoraExame(LocalTime.parse("10:01"));
        exameRequestDTO.setIdPessoa(7);
        exameRequestDTO.setLocalExame("escola - classe 10");
        exameRequestDTO.setNomeExame("Exame CHECK-UP");
        exameRequestDTO.setStatusExame(StatusExameEnum.PENDENTE);
        exameRequestDTO.setTipoExame(TipoExameEnum.COMPLEMENTAR);

        String jsonRequestBody = objectMapper.writeValueAsString(exameRequestDTO);

        exameService.create(exameRequestDTO);

    }
}
