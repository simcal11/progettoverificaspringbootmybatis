package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import it.eagleprojects.progettoverificaspringbootmybatis.service.CorsoService;
import it.eagleprojects.progettoverificaspringbootmybatis.service.StudenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CorsoController2 {

//    @Autowired
//    CorsoMapper corsoMapper;
//
//    @Autowired
//    private StudenteMapper studenteMapper;

    @Autowired
    private CorsoService corsoService;

    @Autowired
    private StudenteService studenteService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("listaCorsi", corsoService.getAllCorsi());
        return "getCorsi";
    }

    @GetMapping("/allCorsi")
    public String mostraCorsi(Model model) {
        model.addAttribute("listaCorsi", corsoService.getAllCorsi());
        return "getCorsi";
    }

    @GetMapping("/insertCorso")
    public String mostraInsertCorsoForm(Model model) {
        model.addAttribute("corso", new Corso());
        return "insertCorso";
    }

    @PostMapping("/insertCorso")
    public String insertCorsoSubmit(@ModelAttribute Corso corso, Model model) {
        corsoService.insertCorso(corso);
        model.addAttribute("corso", corso);
        return "insertCorsoResult";
    }

    @PostMapping(value = "/deleteCorso/{id}")
    public String deleteCorsoById(@PathVariable(name = "id") Long corsoId) {
        corsoService.deleteCorsoById(corsoId);
        return "redirect:/allCorsi";
    }

    //Altro modo
    /*@PostMapping(value = "/deleteCorso")
    public String deleteCorso(@RequestParam String corsoId) {
        corsoService.deleteCorsoById(Long.valueOf(corsoId));
        return "redirect:/allCorsi";
    }*/

    @GetMapping("/updateCorso/{id}")
    public ModelAndView mostraUpdateCorsoForm(@PathVariable(name = "id") Long corsoId) {
        ModelAndView editView = new ModelAndView("updateCorso");
        editView.addObject("corso", corsoService.getCorsoById(corsoId));
        return editView;
    }

    @PostMapping("/updateCorso/{id}")
    public String updateCorsoSubmit(@ModelAttribute Corso corso, Model model) {
        corsoService.updateCorsoById(corso);
        model.addAttribute("corso", corso);
        return "updateCorsoResult";
    }


    @GetMapping("/allStudenti/{id}/corsi")
    public String getAllCorsiByStudenteId(@PathVariable(name = "id") Long studenteId, Model model) {
        model.addAttribute("listaCorsi", corsoService.getAllCorsiByStudenteId(studenteId));
        model.addAttribute("studente", studenteService.getStudenteById(studenteId));
        return "getCorsi";
    }

    @GetMapping("/allStudenti/{id}/addCorso")
    public String addCorsoToStudente(@PathVariable(name = "id") Long studenteId, Model model) {
        model.addAttribute("listaCorsiDisponibili", corsoService.getAllCorsiDisponibiliForStudenteId(studenteId));
        model.addAttribute("studente", studenteService.getStudenteById(studenteId));
        model.addAttribute("corso", new Corso());
        return "insertCorsoToStudente";
    }

    @PostMapping("/allStudenti/{id}/addCorso")
    public String addCorsoToStudenteSubmit(@PathVariable(name = "id") Long studenteId, @ModelAttribute Corso corso, Model model) {
        corsoService.insertCorsoToStudente(studenteId, corso);
        model.addAttribute("corso", corsoService.getCorsoById(corso.getId()));
        model.addAttribute("studente", studenteService.getStudenteById(studenteId));
        return "insertCorsoToStudenteResult";

    }

    @PostMapping(value = "/allStudenti/{studenteId}/removeCorso/{corsoId}")
    public String deleteCorsoFromStudente(@PathVariable(name = "studenteId") Long studenteId, @PathVariable(name = "corsoId") Long corsoId, Model model) {
        //Debug
        /*System.out.println(studenteId);
        for (Corso c : corsoService.getAllCorsiByStudenteId(studenteId)) {
            System.out.println(c.getId());
        }*/

        corsoService.deleteCorsoFromStudente(studenteId, corsoService.getCorsoById(corsoId));
        model.addAttribute("studente", studenteService.getStudenteById(studenteId));
        model.addAttribute("listaCorsi", corsoService.getAllCorsiByStudenteId(studenteId));
        return "getCorsi";
    }


}
