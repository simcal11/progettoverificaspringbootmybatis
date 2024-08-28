package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.CorsiStudentiIscrizioni;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import it.eagleprojects.progettoverificaspringbootmybatis.service.CorsoService;
import it.eagleprojects.progettoverificaspringbootmybatis.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class StudenteController2 {

    @Autowired
    StudenteService studenteService;
    @Autowired
    CorsoService corsoService;

    @GetMapping("/allStudenti")
    public String mostraStudenti(Model model) {
        model.addAttribute("listaStudenti", studenteService.getAllStudenti());
        return "getStudenti";
    }

    @GetMapping("/insertStudente")
    public String mostraInsertStudenteForm(Model model) {
        model.addAttribute("studente", new Studente());
        return "insertStudente";
    }

    @PostMapping("/insertStudente")
    public String insertStudenteSubmit(@ModelAttribute Studente studente, Model model) {
        studenteService.insertStudente(studente);
        model.addAttribute("studente", studente);
        return "insertStudenteResult";
    }

    @PostMapping(value = "/deleteStudente/{id}")
    public String deleteStudenteById(@PathVariable(name = "id") Long studenteId) {
        studenteService.deleteStudenteById(studenteId);
        return "redirect:/allStudenti";
    }

    //Altro modo
    /*@PostMapping(value = "/deleteStudente")
    public String deleteStudente(@RequestParam String studenteId) {
        studenteService.deleteStudenteById(Long.valueOf(studenteId));
        return "redirect:/allStudenti";
    }*/

    @GetMapping("/updateStudente/{id}")
    public ModelAndView mostraUpdateStudenteForm(@PathVariable(name = "id") Long studenteId) {
        ModelAndView editView = new ModelAndView("updateStudente");
        editView.addObject("studente", studenteService.getStudenteById(studenteId));
        return editView;
    }

    @PostMapping("/updateStudente/{id}")
    public String updateStudenteSubmit(@ModelAttribute Studente studente, Model model) {
        studenteService.updateStudenteById(studente);
        model.addAttribute("studente", studente);
        return "updateStudenteResult";
    }

    @GetMapping("/allCorsi/{id}/studenti")
    public String getAllStudentiByCorsoId(@PathVariable(name = "id") Long corsoId, Model model) {
        model.addAttribute("listaStudenti", studenteService.getAllStudentiByCorsoId(corsoId));
        model.addAttribute("corso", corsoService.getCorsoById(corsoId));
        return "getStudenti";
    }


    @GetMapping("/allCorsi/{id}/addStudente")
    public String addStudenteToCorso(@PathVariable(name = "id") Long corsoId, Model model) {
        model.addAttribute("listaStudentiDisponibili", studenteService.getAllStudentiDisponibiliForCorsoId(corsoId));
        model.addAttribute("corso", corsoService.getCorsoById(corsoId));
        model.addAttribute("studente", new Studente());
        return "insertStudenteToCorso";
    }

    @PostMapping("/allCorsi/{id}/addStudente")
    public String addStudenteToCorsoSubmit(@PathVariable(name = "id") Long corsoId, @ModelAttribute Studente studente, Model model) {
        studenteService.insertStudenteToCorso(corsoId, studente);
        model.addAttribute("studente", studenteService.getStudenteById(studente.getId()));
        model.addAttribute("corso", corsoService.getCorsoById(corsoId));
        return "insertStudenteToCorsoResult";
    }



    @PostMapping(value = "/allCorsi/{corsoId}/removeStudente/{studenteId}")
    public String deleteStudenteFromCorso(@PathVariable(name="corsoId") Long corsoId, @PathVariable(name="studenteId") Long studenteId, Model model) {
        studenteService.deleteStudenteFromCorso(corsoId, studenteService.getStudenteById(studenteId));
        model.addAttribute("corso", corsoService.getCorsoById(corsoId));
        model.addAttribute("listaStudenti", studenteService.getAllStudentiByCorsoId(corsoId));
        return "getStudenti";
    }


}
