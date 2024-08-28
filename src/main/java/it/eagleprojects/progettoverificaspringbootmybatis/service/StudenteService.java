package it.eagleprojects.progettoverificaspringbootmybatis.service;

import it.eagleprojects.progettoverificaspringbootmybatis.mapper.StudenteMapper;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudenteService {

    @Autowired
    StudenteMapper studenteMapper;

    public List<Studente> getAllStudenti() {
        return studenteMapper.getAllStudenti();
    }

    public Studente getStudenteById(Long id) {
        return studenteMapper.getStudenteById(id);
    }

    public void insertStudente(Studente studente) {
        studenteMapper.insertStudente(studente);
    }

    public void deleteStudenteById(Long id){
        studenteMapper.deleteStudenteById(id);
    }

    public void updateStudenteById(Studente studente){
        studenteMapper.updateStudenteById(studente);
    }

    public List<Studente> getAllStudentiByCorsoId(Long corsoId) {
        return studenteMapper.getAllStudentiByCorsoId(corsoId);
    }

    public List<Studente> getAllStudentiDisponibiliForCorsoId(Long corsoId) {
        List<Studente> listaAllStudenti = studenteMapper.getAllStudenti();
        List<Studente> listaStudentiByCorsoId = studenteMapper.getAllStudentiByCorsoId(corsoId);

        for (Studente studente : listaStudentiByCorsoId) {
            for (Studente studente2 : listaAllStudenti) {
                if (studente.getId().equals(studente2.getId())) {
                    listaAllStudenti.remove(studente2);
                    //Debug
                    break;
                }
            }
        }

        return listaAllStudenti;
    }

    public void insertStudenteToCorso(Long corsoId, Studente studente) {
        studenteMapper.insertStudenteToCorso(corsoId, studente);
    }

    public void deleteStudenteFromCorso(Long corsoId,  Studente studente){
        studenteMapper.deleteStudenteFromCorso(corsoId, studente);
    }


}
