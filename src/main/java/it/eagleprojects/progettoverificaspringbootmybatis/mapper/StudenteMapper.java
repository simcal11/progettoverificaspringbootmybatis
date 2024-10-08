package it.eagleprojects.progettoverificaspringbootmybatis.mapper;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudenteMapper {

    List<Studente> getAllStudenti();

    Studente getStudenteById(Long studenteId);

    Studente getStudenteByMatricola(String matricola);

    List<Studente> getAllStudentiByCorsoId(Long corsoId);

    void insertStudente(Studente studente);

    void insertStudenteToCorso(Long corsoId, Studente studente);

    void updateStudenteById(Studente studente);

    void deleteStudenteById(Long studenteId);

    void deleteAllStudenti();

    void deleteStudenteFromCorso(Long corsoId, Studente studente);

}
