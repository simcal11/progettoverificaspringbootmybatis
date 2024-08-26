package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CorsoController2 {

    @Autowired
    CorsoMapper corsoMapper;

    @Autowired
    private StudenteMapper studenteMapper;

    @GetMapping("/allCorsi")
    public String mostraCorsi(Model model) {
        List<Corso> listaCorsi =  corsoMapper.getAllCorsi();

        model.addAttribute("listaCorsi", listaCorsi);
        return "getCorsi";
    }

    @GetMapping("/insertCorso")
    public String mostraInsertCorsoForm(Model model) {
        model.addAttribute("corso", new Corso());
        return "insertCorso";
    }

    @PostMapping("/insertCorso")
    public String insertCorsoSubmit(@ModelAttribute Corso corso, Model model) {
        corsoMapper.insertCorso(corso);
        model.addAttribute("corso", corso);
        return "insertCorsoResult";
    }

    @PostMapping(value = "/deleteCorso")
    public String deleteCorso(@RequestParam String corsoId) {

        corsoMapper.deleteCorsoById(Long.valueOf(corsoId));
        return "redirect:/allCorsi";
    }

    @GetMapping("/updateCorso/{id}")
    public ModelAndView mostraUpdateCorsoForm(@PathVariable(name="id") Long corsoId) {
        ModelAndView editView = new ModelAndView("updateCorso");
        Corso corso = corsoMapper.getCorsoById(corsoId);
        corsoMapper.updateCorsoById(corso);
        editView.addObject("corso", corso);
        return editView;
    }

    @PostMapping("/updateCorso")
    public String updateCorsoSubmit(@ModelAttribute Corso corso, Model model) {
        corsoMapper.updateCorsoById(corso);
        model.addAttribute("corso", corso);
        return "updateCorsoResult";
    }

    @GetMapping("/allCorsi/{id}/studenti")
    public String getAllStudentiByCorsoId(@PathVariable(name="id") Long corsoId, Model model) {
        List<Studente> listaStudentiByCorsoId =  studenteMapper.getAllStudentiByCorsoId(corsoId);

        model.addAttribute("listaStudenti", listaStudentiByCorsoId);
        model.addAttribute("corso", corsoMapper.getCorsoById(corsoId));
        return "getStudenti";
    }


    @GetMapping("/allCorsi/{id}/addStudente")
    public String addStudenteToCorso(@PathVariable(name="id") Long corsoId, Model model) {
        List<Studente> listaAllStudenti =  studenteMapper.getAllStudenti();
        List<Studente> listaStudentiByCorsoId =  studenteMapper.getAllStudentiByCorsoId(corsoId);

        for (Studente studente : listaStudentiByCorsoId) {
            for(Studente studente2 : listaAllStudenti) {
                if(studente.getId().equals(studente2.getId())) {
                    listaAllStudenti.remove(studente2);
                    //Debug
                    break;
                }
            }
        }

        model.addAttribute("listaStudentiDisponibili", listaAllStudenti);
        model.addAttribute("corso", corsoMapper.getCorsoById(corsoId));
        model.addAttribute("studente", new Studente());
        return "insertStudenteToCorso";
    }

    @PostMapping("/allCorsi/{id}/addStudente")
    public String addStudenteToCorsoSubmit(@PathVariable(name="id") Long corsoId, @ModelAttribute Studente studente, Model model) {

        studente = studenteMapper.getStudenteById(studente.getId());
        Corso corso = corsoMapper.getCorsoById(corsoId);

        studenteMapper.insertStudenteToCorso(corsoId, studente);

        model.addAttribute("studente", studente);
        model.addAttribute("corso", corso);
        return "insertStudenteToCorsoResult";

    }


    @PostMapping(value = "/allStudenti/{studenteId}/removeCorso/{corsoId}")
    public String deleteCorsoFromStudente(@PathVariable(name="studenteId") Long studenteId, @PathVariable(name="corsoId") Long corsoId, Model model) {

        Corso corso = corsoMapper.getCorsoById(corsoId);

        corsoMapper.deleteCorsoFromStudente(studenteId, corso);

        model.addAttribute("studente", studenteMapper.getStudenteById(studenteId));
        model.addAttribute("listaCorsi", corsoMapper.getAllCorsiByStudenteId(studenteId));

        return "getStudenti";
    }


}
