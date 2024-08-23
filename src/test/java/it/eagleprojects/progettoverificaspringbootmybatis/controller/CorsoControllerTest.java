package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import org.json.JSONObject;

import org.junit.Test;

import org.junit.jupiter.api.Order;

import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Alphanumeric;

import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest()
@OrderWith(Alphanumeric.class)
public class CorsoControllerTest {


    static Corso corsoProgrammazione = new Corso(1L, "Programmazione 1", 6, 70, null);



    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testA1GetAllCorsiTest() throws Exception {

       this.mockMvc
                .perform(MockMvcRequestBuilders.get("/corsi").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    public void testA2GetStudenteByIdTest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi/" + corsoProgrammazione.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testA3InsertStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Corso Nuovo\", \"cfu\": 9, \"ore\": 60}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/corsi").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isCreated());
    }

    @Test
    public void testA4GetStudenteByNomeTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi/nome/" + "Corso Nuovo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Corso Nuovo"));
    }

    @Test
    public void testA5UpdateStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Corso Nuovo\", \"cfu\": 33, \"ore\": 60}";

        MvcResult ritorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi/nome/" + "Corso Nuovo"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(ritorno.getResponse().getContentAsString());
        Long id = Long.valueOf(jsonObject.get("id").toString());

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/corsi/{corsoId}", id.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                ).andExpect(status().isNoContent());
    }

    @Test
    public void testA6DeleteCorsoByIdTest() throws Exception {

        MvcResult ritorno = this.mockMvc.perform(MockMvcRequestBuilders.get("/corsi/nome/" + "Corso Nuovo"))
                .andReturn();

        JSONObject jsonObject = new JSONObject(ritorno.getResponse().getContentAsString());
        Long id = Long.valueOf(jsonObject.get("id").toString());


        this.mockMvc.perform(MockMvcRequestBuilders.delete("/corsi/" + id.toString()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testA7InsertCorsoToStudenteTest() throws Exception {

        Long idStudente = 3L;
        Long idCorso = 1L;
        String requestBody = "{\"id\":"+idCorso.toString() +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/studenti/"+idStudente.toString()+"/corsi").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isCreated());
    }

    @Test
    public void testA8DeleteCorsoFromStudenteTest() throws Exception {

        Long idStudente = 3L;
        Long idCorso = 1L;
        String requestBody = "{\"id\":"+idCorso.toString() +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/studenti/"+idStudente.toString()+"/corsi").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isNoContent());
    }
}
