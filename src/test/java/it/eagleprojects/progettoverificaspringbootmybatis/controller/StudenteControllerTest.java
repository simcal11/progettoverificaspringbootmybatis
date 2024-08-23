package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import io.swagger.v3.core.util.Json;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Alphanumeric;
import org.mockito.InjectMocks;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest()
@OrderWith(Alphanumeric.class)
public class StudenteControllerTest {


    static Studente studenteMarioRossi = new Studente(1L, "Mario", "Rossi", "ARO76",
            "mariorossi@email.com", null);



    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testA1GetAllStudentiTest() throws Exception {

       this.mockMvc
                .perform(MockMvcRequestBuilders.get("/studenti").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testA2GetStudenteByIdTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/" + studenteMarioRossi.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testA3InsertStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Studente Nuovo\", \"cognome\": \"Update\", \"matricola\": \"prova\", \"email\": \"nuova@gmail.com\"}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isCreated());
    }

    @Test
    public void testA4GetStudenteByMatricolaTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/matricola/" + "prova"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matricola").value("prova"));
    }

    @Test
    public void testA5UpdateStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Studente Update\", \"cognome\": \"Update\", \"matricola\": \"prova\", \"email\": \"nuova@gmail.com\"}";

        MvcResult ritorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/matricola/" + "prova"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(ritorno.getResponse().getContentAsString());
        Long id = Long.valueOf(jsonObject.get("id").toString());

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/studenti/{studenteId}", id.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                ).andExpect(status().isNoContent());
    }

    @Test
    public void testA6DeleteStudenteByIdTest() throws Exception {
        MvcResult ritorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/matricola/" + "prova"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(ritorno.getResponse().getContentAsString());
        Long id = Long.valueOf(jsonObject.get("id").toString());


        this.mockMvc.perform(MockMvcRequestBuilders.delete("/studenti/" + id.toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testA7InsertStudenteToCorsoTest() throws Exception {

        Long idStudente = 3L;
        Long idCorso = 1L;
        String requestBody = "{\"id\":"+idStudente.toString() +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/corsi/"+idCorso.toString()+"/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isCreated());
    }

    @Test
    public void testA8DeleteStudenteFromCorsoTest() throws Exception {

        Long idStudente = 3L;
        Long idCorso = 1L;
        String requestBody = "{\"id\":"+idStudente.toString() +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/corsi/"+idCorso.toString()+"/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isNoContent());
    }
}
