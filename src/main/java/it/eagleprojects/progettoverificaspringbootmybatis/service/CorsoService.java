package it.eagleprojects.progettoverificaspringbootmybatis.service;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.CorsoMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class CorsoService {

    @Autowired
    CorsoMapper corsoMapper;

    public List<Corso> getAllCorsi() {
        return corsoMapper.getAllCorsi();
    }

    public Corso getCorsoById(Long corsoId) {
        return corsoMapper.getCorsoById(corsoId);
    }

    public void insertCorso(Corso corso){
        corsoMapper.insertCorso(corso);
    }

    public void deleteCorsoById(Long corsoId){
        corsoMapper.deleteCorsoById(corsoId);
    }

    public void updateCorsoById(Corso corso){
        corsoMapper.updateCorsoById(corso);
    }

    public List<Corso> getAllCorsiByStudenteId(Long studenteId) {
        return corsoMapper.getAllCorsiByStudenteId(studenteId);
    }
    public List<Corso> getAllCorsiDisponibiliForStudenteId(Long studenteId) {
        List<Corso> listaAllCorsi =  corsoMapper.getAllCorsi();
        List<Corso> listaCorsiByStudenteId =  corsoMapper.getAllCorsiByStudenteId(studenteId);

        for (Corso corso : listaCorsiByStudenteId) {
            for(Corso corso2 : listaAllCorsi) {
                if(corso.getId().equals(corso2.getId())) {
                    listaAllCorsi.remove(corso2);
                    break;
                }
            }
        }

        //Debug
        /*for(Corso corso: listaCorsiByStudenteId){
            System.out.println(corso.getId() +" " +corso.getNome()+" " +corso.getCfu() +" " +corso.getOre() +" " +corso.getStudenti());
        }
        System.out.println("----------------------------------------------------------");
         for(Corso corso: listaAllCorsi){
             System.out.println(corso.getId() +" " +corso.getNome()+" " +corso.getCfu() +" " +corso.getOre() +" " +corso.getStudenti());
         }*/

        return listaAllCorsi;
    }

    public void insertCorsoToStudente(Long studenteId, Corso corso){
        corsoMapper.insertCorsoToStudente(studenteId, corso);
    }

    public void deleteCorsoFromStudente(Long studenteId,  Corso corso){
        corsoMapper.deleteCorsoFromStudente(studenteId, corso);
    }


}
