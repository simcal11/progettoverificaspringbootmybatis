package it.eagleprojects.progettoverificaspringbootmybatis.model;

import java.io.Serializable;
import java.util.Objects;

public class Studente implements Serializable {

    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String matricola;


    public Studente() {

    }

    public Studente(Long id, String nome, String cognome, String email, String matricola
    ) {
        super();
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.matricola = matricola;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Studente studente = (Studente) o;
        return Objects.equals(id, studente.id) && Objects.equals(nome, studente.nome) && Objects.equals(cognome, studente.cognome) && Objects.equals(email, studente.email) && Objects.equals(matricola, studente.matricola);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cognome, email, matricola);
    }
}
