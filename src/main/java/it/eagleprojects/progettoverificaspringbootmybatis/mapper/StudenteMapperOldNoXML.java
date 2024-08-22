package it.eagleprojects.progettoverificaspringbootmybatis.mapper;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudenteMapperOldNoXML {

    @Select("select id, nome, cognome, matricola, email from studenti where id = #{id}")
    Studente findById(@Param("id") Long id);

    @Select("select id, nome, cognome, matricola, email from studenti")
    List<Studente> findAll();

    @Insert("insert into studenti (nome,cognome, matricola, email) values(#{nome}, #{cognome},#{matricola}, #{email})")
    void add(Studente studente);


    @Update("update studenti set nome=#{studente.nome}, cognome=#{studente.cognome}, matricola=#{studente.matricola}, email=#{studente.email} where id=#{id}")
    void update(Studente studente, Long id);

    @Delete("delete from studenti where id = #{id}")
    void delete(Long id);

    @Delete("delete from studenti")
    void deleteAll();
}

