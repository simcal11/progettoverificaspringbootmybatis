package it.eagleprojects.progettoverificaspringbootmybatis.controller;


import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.json.JSONObject;
import org.junit.Test;
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
        long id = Long.parseLong(jsonObject.get("id").toString());

        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.put("/studenti/{studenteId}", Long.toString(id))
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
        long id = Long.parseLong(jsonObject.get("id").toString());


        this.mockMvc.perform(MockMvcRequestBuilders.delete("/studenti/" + Long.toString(id)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testA7InsertStudenteToCorsoTest() throws Exception {

        long idStudente = 3L;
        long idCorso = 1L;
        String requestBody = "{\"id\":"+ Long.toString(idStudente) +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/corsi/"+ Long.toString(idCorso) +"/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isCreated());
    }

    @Test
    public void testA8DeleteStudenteFromCorsoTest() throws Exception {

        long idStudente = 3L;
        long idCorso = 1L;
        String requestBody = "{\"id\":"+ Long.toString(idStudente) +"}";


        this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/corsi/"+ Long.toString(idCorso) +"/studenti").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                ).andExpect(status().isNoContent());
    }
}
