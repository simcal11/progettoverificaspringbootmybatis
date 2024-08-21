package it.eagleprojects.progettoverificaspringbootmybatis.mapper;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudenteMapper {

    @Select("select id, nome, cognome, matricola, email from studenti where id = #{id}")
    Studente findById(@Param("id") Long id);

    @Select("select id, nome, cognome, matricola, email from studenti")
    List<Studente> findAll();

    @Insert("insert into studenti(nome,cognome, matricola, email) values(nome=#{nome},cognome=#{cognome}, matricola=#{matricola}, email=#{email})")
    void add(Studente studente);


    @Update("update studenti set nome=#{nome},cognome=#{cognome}, matricola=#{matricola}, email=#{email} where id=#{id}")
    void update(Studente studente, Long id);

    @Delete("delete from studenti where id = #{id}")
    void delete(Long id);

    @Delete("delete from studenti")
    void deleteAll();
}

