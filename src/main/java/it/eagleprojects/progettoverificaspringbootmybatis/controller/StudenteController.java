package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import io.swagger.v3.oas.annotations.Operation;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/studenti")
@RestController
public class StudenteController {

    @Autowired
    private StudenteMapper studenteMapper;

    @Operation(summary = "Metodo che permette di ottenere tutti gli Studenti")
    @GetMapping()
    @ResponseBody
    public List<Studente> getAllStudenti() {
        return studenteMapper.findAll();
    }

    @Operation(summary = "Metodo che permette di ottenere uno Studente")
    @GetMapping("/{id}")
    @ResponseBody Studente getStudenteById(@PathVariable("id") Long id) {
        return studenteMapper.findById(id);
    }

    @Operation(summary = "Metodo che permette di aggiungere uno Studente")
    @PostMapping()
    public void insertStudente(@RequestBody Studente studente){
        studenteMapper.add(studente);
    }

    @Operation(summary = "Metodo che permette di aggiornare uno Studente")
    @PutMapping("{id}")
    public void updateStudente(@RequestBody Studente studente,@PathVariable("id") Long id){
        studenteMapper.update(studente,id);
    }

    @Operation(summary = "Metodo che permette di eliminare tutti gli Studenti")
    @DeleteMapping()
    public void deleteAllStudenti() {
        studenteMapper.deleteAll();
    }

    @Operation(summary = "Metodo che permette di eliminare uno Studente")
    @DeleteMapping("{id}")
    public void deleteStudente(@PathVariable("id") Long id){
        studenteMapper.delete(id);
    }
}
