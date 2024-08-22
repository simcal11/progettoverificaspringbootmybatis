package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest(StudenteController.class)
public class StudenteControllerTest {

    static Studente studenteMarioRossi = new Studente(Long.valueOf(1), "Mario", "Rossi", "ARO76",
            "mariorossi@email.com", null);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStudentiTest() throws Exception {

       this.mockMvc
                .perform(MockMvcRequestBuilders.get("/studenti").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)))
               .andExpect(jsonPath("$[0].id").value(1));

    }

    @Test
    public void getStudenteByIdTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get("/studenti/" + studenteMarioRossi.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void insertStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Studente Nuovo\", \"cognome\": \"Bianchi\", \"matricola\": \"ABDC43\", \"email\": \"nuova@gmail.com\"}";

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isOk());
    }

    @Test
    public void updateStudenteTest() throws Exception {

        String requestBody = "{\"nome\": \"Studente Update\", \"cognome\": \"Bianchi\", \"matricola\": \"ABDC43\", \"email\": \"nuova@gmail.com\"}";


        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/studenti/{studenteId}", "4")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                ).andExpect(status().isOk());
    }

    @Test
    public void deleteStudenteByIdTest() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/studenti/" + "4"))
                .andExpect(status().isOk());
    }
}
