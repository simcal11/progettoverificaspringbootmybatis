package it.eagleprojects.progettoverificaspringbootmybatis.mapper;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Corso;
import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CorsoMapper {

    List<Corso> getAllCorsi();

    Corso getCorsoById(Long corsoId);

    Corso getCorsoByNome(String corsoNome);

    void insertCorso(Corso corso);

    void updateCorsoById(Corso corso);

    void deleteCorsoById(Long corsoId);

    void deleteAllCorsi();

}
