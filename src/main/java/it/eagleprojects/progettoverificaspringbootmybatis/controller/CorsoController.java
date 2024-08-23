package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping()
@RestController
public class CorsoController {


    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    private StudenteMapper studenteMapper;

    @Operation(summary = "Metodo che permette di ottenere tutti i Corsi")
    @GetMapping("corsi")
    @ResponseBody
    public ResponseEntity<List<Corso>> getAllCorsi() {
        return new ResponseEntity<>(corsoMapper.getAllCorsi(), HttpStatus.OK);
    }

    @Operation(summary = "Metodo che permette di ottenere un Corso dato l'id")
    @GetMapping("/corsi/{corsoId}")
    @ResponseBody
    public ResponseEntity<Corso> getCorsobyId(@PathVariable("corsoId") Long corsoId) throws Exception {
        Corso corso = corsoMapper.getCorsoById(corsoId);
        if (corso == null) {
            throw new Exception("Non esiste un Corso con id = " + corsoId);
        }
        return new ResponseEntity<>(corso, HttpStatus.OK);
    }

    @Operation(summary = "Metodo che permette di ottenere un Corso dato il nome")
    @GetMapping("/corsi/nome/{corsoNome}")
    @ResponseBody
    public ResponseEntity<Corso> getCorsobyNome(@PathVariable("corsoNome") String corsoNome) throws Exception {
        Corso corso = corsoMapper.getCorsoByNome(corsoNome);
        if (corso == null) {
            throw new Exception("Non esiste un Corso con nome = " + corsoNome);
        }
        return new ResponseEntity<>(corso, HttpStatus.OK);
    }


    @Operation(summary = "Metodo che permette di ottenere i Corsi frequentati da uno Studente dato lo studenteId")
    @GetMapping( "/studenti/{studenteId}/corsi")
    public @ResponseBody ResponseEntity<List<Corso>> getAllCorsiByStudenteId(@PathVariable("studenteId") Long studenteId)
            throws Exception {
        if (studenteMapper.getStudenteById(studenteId) == null) {
            throw new Exception("Non esiste uno Studente con id = " + studenteId);
        } else {
            return new ResponseEntity<>(corsoMapper.getAllCorsiByStudenteId(studenteId), HttpStatus.OK);
        }
    }


    @Operation(summary = "Metodo che permette di aggiungere un Corso")
    @PostMapping("/corsi")
    public ResponseEntity<Corso> insertCorso(@RequestBody Corso corso){
        corsoMapper.insertCorso(corso);
        return new ResponseEntity<>(corso,  HttpStatus.CREATED);
    }


    @Operation(summary = "Metodo che permette di aggiungere un Corso ad uno Studente")
    @PostMapping("/studenti/{studenteId}/corsi")
    public @ResponseBody ResponseEntity<Corso>  insertCorsoToStudente(@PathVariable(value = "studenteId") long studenteId,
                                                                   @RequestBody Corso corso) throws Exception {
        if (studenteMapper.getStudenteById(studenteId) == null) {
            throw new Exception("Non esiste uno Studente con id = " + studenteId);
        }

        if (corso.getId() == null) {
            throw new Exception("Non è stato specifica l'id del corso da aggiungere " + corso.getId());
        }

        if (corsoMapper.getCorsoById(corso.getId()) == null) {
            throw new Exception("Non esiste un Corso con id = " + corso.getId());
        }

        corsoMapper.insertCorsoToStudente(studenteId, corso);

        return new ResponseEntity<>(corso, HttpStatus.CREATED);
    }


    @Operation(summary = "Metodo che permette di aggiornare un Corso")
    @PutMapping("corsi/{corsodId}")
    public ResponseEntity<Corso> updateCorsoById(@RequestBody Corso corsoRequest, @PathVariable("corsodId") Long corsoId) throws Exception {

        Corso corsoDaAggiornare = corsoMapper.getCorsoById(corsoId);

        if (corsoDaAggiornare == null) {
            throw new Exception("Non esiste un Corso con id = " + corsoId);
        }

        corsoRequest.setId(corsoDaAggiornare.getId());

        if (corsoRequest.getNome() == null) {
            corsoRequest.setNome(corsoDaAggiornare.getNome());
        }

        if (corsoRequest.getCfu() == null) {
            corsoRequest.setCfu(corsoDaAggiornare.getCfu());
        }

        if (corsoRequest.getOre() == null) {
            corsoRequest.setOre(corsoDaAggiornare.getOre());
        }

        if (corsoRequest.getStudenti() == null) {
            corsoRequest.setStudenti(corsoDaAggiornare.getStudenti());
        }

        corsoMapper.updateCorsoById(corsoRequest);

        return new ResponseEntity<>(corsoRequest, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare tutti i Corsi")
    @DeleteMapping("/corsi")
    public  ResponseEntity<Corso>  deleteAllCorsi() {
        corsoMapper.deleteAllCorsi();
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare uno Studente")
    @DeleteMapping("corsi/{corsoId}")
    public ResponseEntity<Corso>  deleteStudente(@PathVariable("corsoId") Long corsoId){
        corsoMapper.deleteCorsoById(corsoId);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Metodo che permette di eliminare uno Studente")
    @DeleteMapping("/studenti/{studenteId}/corsi")
    public @ResponseBody ResponseEntity<Corso>  deleteCorsoFromStudente(@PathVariable(value = "studenteId") Long studenteId,
                                                                        @RequestBody Corso corso) throws Exception {

        if (studenteMapper.getStudenteById(studenteId) == null) {
            throw new Exception("Non esiste uno Studente con id = " + studenteId);
        }

        if (corso.getId() == null) {
            throw new Exception("Non è stato specifica l'id del Corso da eliminare " + corso.getId());
        }

        if (corsoMapper.getCorsoById(corso.getId()) == null) {
            throw new Exception("Non esiste un Corso con id = " + corso.getId());
        }

        corsoMapper.deleteCorsoFromStudente(studenteId, corso);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
