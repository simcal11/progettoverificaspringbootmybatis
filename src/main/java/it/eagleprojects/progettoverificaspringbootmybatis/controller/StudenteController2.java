package it.eagleprojects.progettoverificaspringbootmybatis.controller;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.CorsiStudentiIscrizioni;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class StudenteController2 {

    @Autowired
    StudenteMapper studenteMapper;
    @Autowired
    private CorsoMapper corsoMapper;

    @GetMapping("/allStudenti")
    public String mostraStudenti(Model model) {
        List<Studente> listaStudenti =  studenteMapper.getAllStudenti();

        model.addAttribute("listaStudenti", listaStudenti);
        return "getStudenti";
    }

    @GetMapping("/insertStudente")
    public String mostraInsertStudenteForm(Model model) {
        model.addAttribute("studente", new Studente());
        return "insertStudente";
    }

    @PostMapping("/insertStudente")
    public String insertStudenteSubmit(@ModelAttribute Studente studente, Model model) {
        studenteMapper.insertStudente(studente);
        model.addAttribute("studente", studente);
        return "insertStudenteResult";
    }

    @PostMapping(value = "/deleteStudente")
    public String deleteStudente(@RequestParam String studenteId) {

        studenteMapper.deleteStudenteById(Long.valueOf(studenteId));
        return "redirect:/allStudenti";
    }

    @GetMapping("/updateStudente/{id}")
    public ModelAndView mostraUpdateStudenteForm(@PathVariable(name="id") Long studenteId) {
        ModelAndView editView = new ModelAndView("updateStudente");
        Studente studente = studenteMapper.getStudenteById(studenteId);
        editView.addObject("studente", studente);
        return editView;
    }

    @PostMapping("/updateStudente")
    public String updateStudenteSubmit(@ModelAttribute Studente studente, Model model) {
        studenteMapper.updateStudenteById(studente);
        model.addAttribute("studente", studente);
        return "updateStudenteResult";
    }

    @GetMapping("/allStudenti/{id}/corsi")
    public String getAllCorsiByStudenteId(@PathVariable(name="id") Long studenteId, Model model) {

        model.addAttribute("listaCorsi", corsoMapper.getAllCorsiByStudenteId(studenteId));
        model.addAttribute("studente", studenteMapper.getStudenteById(studenteId));
        return "getCorsi";
    }

    @GetMapping("/allStudenti/{id}/addCorso")
    public String addCorsoToStudente(@PathVariable(name="id") Long studenteId, Model model) {
        List<Corso> listaAllCorsi =  corsoMapper.getAllCorsi();
        List<Corso> listaCorsiByStudenteId =  corsoMapper.getAllCorsiByStudenteId(studenteId);

        for (Corso corso : listaCorsiByStudenteId) {
            for(Corso corso2 : listaAllCorsi) {
                if(corso.getId().equals(corso2.getId())) {
                    listaAllCorsi.remove(corso2);
                    //Debug
//                    System.out.println("Rimosso "+corso2.getNome());
                    break;
                }
            }
        }


        //Debug
//        for(Corso corso: listaCorsiByStudenteId){
//            System.out.println(corso.getId() +" " +corso.getNome()+" " +corso.getCfu() +" " +corso.getOre() +" " +corso.getStudenti());
//        }
//        System.out.println("----------------------------------------------------------");
//         for(Corso corso: listaAllCorsi){
//             System.out.println(corso.getId() +" " +corso.getNome()+" " +corso.getCfu() +" " +corso.getOre() +" " +corso.getStudenti());
//         }


        model.addAttribute("listaCorsiDisponibili", listaAllCorsi);
        model.addAttribute("studente", studenteMapper.getStudenteById(studenteId));
        model.addAttribute("corso", new Corso());
        return "insertCorsoToStudente";
    }

    @PostMapping("/allStudenti/{id}/addCorso")
    public String addCorsoToStudenteSubmit(@PathVariable(name="id") Long studenteId, @ModelAttribute Corso corso, Model model) {

        corso = corsoMapper.getCorsoById(corso.getId());
        Studente studente = studenteMapper.getStudenteById(studenteId);

        corsoMapper.insertCorsoToStudente(studenteId, corso);

        model.addAttribute("corso", corso);
        model.addAttribute("studente", studente);
        return "insertCorsoToStudenteResult";

    }

    @PostMapping(value = "/allCorsi/{corsoId}/removeStudente/{studenteId}")
    public String deleteStudenteFromCorso(@PathVariable(name="corsoId") Long corsoId, @PathVariable(name="studenteId") Long studenteId, Model model) {

        Studente studente = studenteMapper.getStudenteById(studenteId);

        studenteMapper.deleteStudenteFromCorso(corsoId, studente);

        model.addAttribute("corso", corsoMapper.getCorsoById(corsoId));
        model.addAttribute("listaStudenti", studenteMapper.getAllStudentiByCorsoId(corsoId));

        return "getStudenti";
    }






}
