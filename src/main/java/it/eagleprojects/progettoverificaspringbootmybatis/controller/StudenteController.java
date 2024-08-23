package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
@RestController
public class StudenteController {


    @Autowired
    private StudenteMapper studenteMapper;

    @Autowired
    private CorsoMapper corsoMapper;

    @Operation(summary = "Metodo che permette di ottenere tutti gli Studenti")
    @GetMapping("studenti")
    @ResponseBody
    public ResponseEntity<List<Studente>> getAllStudenti() {
        return new ResponseEntity<>(studenteMapper.getAllStudenti(), HttpStatus.OK);
    }

    @Operation(summary = "Metodo che permette di ottenere uno Studente dato l'id")
    @GetMapping("/studenti/{studenteId}")
    @ResponseBody
    public ResponseEntity<Studente> getStudenteById(@PathVariable("studenteId") Long studenteId) throws Exception {
        Studente studente = studenteMapper.getStudenteById(studenteId);
        if (studente == null) {
            throw new Exception("Non esiste uno Studente con id = " + studenteId);
        }
        return new ResponseEntity<>(studente, HttpStatus.OK);
    }

    @Operation(summary = "Metodo che permette di ottenere uno Studente data la matricola")
    @GetMapping("/studenti/matricola/{matricola}")
    @ResponseBody
    public ResponseEntity<Studente> getStudenteByMatricola(@PathVariable("matricola") String matricola) throws Exception {
        Studente studente = studenteMapper.getStudenteByMatricola(matricola);
        if (studente == null) {
            throw new Exception("Non esiste uno Studente con matricola = " + matricola);
        }
        return new ResponseEntity<>(studente, HttpStatus.OK);
    }


    @Operation(summary = "Metodo che permette di ottenere gli Studenti iscritti ad un Corso dato il corsoId")
    @GetMapping( "/corsi/{corsoId}/studenti")
    public @ResponseBody ResponseEntity<List<Studente>> getAllStudentiByCorsoId(@PathVariable("corsoId") Long corsoId)
            throws Exception {
        if (corsoMapper.getCorsoById(corsoId) == null) {
            throw new Exception("Non esiste un Corso con id = " + corsoId);
        } else {
            return new ResponseEntity<>(studenteMapper.getAllStudentiByCorsoId(corsoId), HttpStatus.OK);
        }
    }


    @Operation(summary = "Metodo che permette di aggiungere uno Studente")
    @PostMapping("/studenti")
    public ResponseEntity<Studente> insertStudente(@RequestBody Studente studente){

        studenteMapper.insertStudente(studente);
        return new ResponseEntity<>(studente,  HttpStatus.CREATED);
    }


    @Operation(summary = "Metodo che permette di aggiungere uno Studente ad un Corso")
    @PostMapping("/corsi/{corsoId}/studenti")
    public @ResponseBody ResponseEntity<Studente> insertStudenteToCorso(@PathVariable(value = "corsoId") Long corsoId,
                                                                     @RequestBody Studente studente) throws Exception {

        if (corsoMapper.getCorsoById(corsoId) == null) {
            throw new Exception("Non esiste un Corso con id = " + corsoId);
        }

        if (studente.getId() == null) {
            throw new Exception("Non è stato specifica l'id dello studente da aggiungere " + studente.getId());
        }

        if (studenteMapper.getStudenteById(studente.getId()) == null) {
            throw new Exception("Non esiste uno Studente con id = " + studente.getId());
        }

        studenteMapper.insertStudenteToCorso(corsoId, studente);

        return new ResponseEntity<>(studente, HttpStatus.CREATED);

    }



    @Operation(summary = "Metodo che permette di aggiornare uno Studente")
    @PutMapping("studenti/{studenteId}")
    public ResponseEntity<Studente> updateStudenteById(@RequestBody Studente studenteRequest, @PathVariable("studenteId") Long studenteId) throws Exception {

        Studente studenteDaAggiornare = studenteMapper.getStudenteById(studenteId);

        if (studenteDaAggiornare == null) {
            throw new Exception("Non esiste uno Studente con id = " + studenteId);
        }

        studenteRequest.setId(studenteDaAggiornare.getId());

        if (studenteRequest.getNome() == null) {
            studenteRequest.setNome(studenteDaAggiornare.getNome());
        }

        if (studenteRequest.getCognome() == null) {
            studenteRequest.setCognome(studenteDaAggiornare.getCognome());
        }

        if (studenteRequest.getEmail() == null) {
            studenteRequest.setEmail(studenteDaAggiornare.getEmail());
        }

        if (studenteRequest.getMatricola() == null) {
            studenteRequest.setMatricola(studenteDaAggiornare.getMatricola());
        }

        if (studenteRequest.getCorsi() == null) {
            studenteRequest.setCorsi(studenteDaAggiornare.getCorsi());
        }

        studenteMapper.updateStudenteById(studenteRequest);

        return new ResponseEntity<>(studenteRequest, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare tutti gli Studenti")
    @DeleteMapping("/studenti")
    public ResponseEntity<Studente> deleteAllStudenti() {
        studenteMapper.deleteAllStudenti();
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare uno Studente")
    @DeleteMapping("studenti/{studenteId}")
    public ResponseEntity<Studente>  deleteStudente(@PathVariable("studenteId") Long studenteId){
        studenteMapper.deleteStudenteById(studenteId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare uno Studente da un Corso")
    @DeleteMapping("corsi/{corsoId}/studenti")
    public @ResponseBody ResponseEntity<Studente>  deleteStudenteFromCorso(@PathVariable(value = "corsoId") Long corsoId,
                                                                           @RequestBody Studente studente) throws Exception {

        if (corsoMapper.getCorsoById(corsoId) == null) {
            throw new Exception("Non esiste un Corso con id = " + corsoId);
        }

        if (studente.getId() == null) {
            throw new Exception("Non è stato specifica l'id dello studente da eliminare " + studente.getId());
        }

        if (studenteMapper.getStudenteById(studente.getId()) == null) {
            throw new Exception("Non esiste uno Studente con id = " + studente.getId());
        }

        studenteMapper.deleteStudenteFromCorso(corsoId, studente);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
