## Progetto Verifica Springboot Mybatis

# Git
1. Creazione di questa repository per il versionamento del codice per lo sviluppo del Progetto Verifica

# Docker
1. Creazione di un docker-compose in cui vengono definiti 2 container: il primo per l'hosting della webapp, realizzato con un Dockerfile a partire da una image alpine e dal file .jar creato con Maven
    1. Definizione di una sottorete 172.50.0.0 con cui il container alpine si connette al container postgres
    1. Definizione di un volume in modo tale da mantere salvate le modifiche che ogni volta vengono effettuate al database
1. 

# Flyway
1. Versionamento del database con la creazione di 6 migration, 3 per la creazione delle tabelle studenti, corsi, corsi_studenti_iscrizioni e 3 per il loro popolamento
2. Definizione del file flyway.conf in cui si impostano le configurazioni baisilari come, url, schemas, user, password.

# Spring
1. Realizzazione di una webapp con  Springboot e MyBatis
    1. Creazione del pom.xml per le dipendenze
    1. Creazione del file application.properties per la configurazione basilare di spring e per la connessione al database postgres
    1. Creazione del file mybatis-config.xml per la configurazione dei mapper
    1. Creazione del package model con Studente.java, Corso.java e CorsiStudentiIscrizioni.java
    1. Creazione del package mapper con i mapper StudenteMapper.java, CorsoMapper.java
    1. Creazione del package controller per:
       1. StudenteController e CorsoController definiscono le classi controller con i metodi per le API Rest come spiegato nel Corso Spring
       1. StudenteController2 e CorsoController2 definiscono invece le classi controller con i metodi per le API Rest che permettono la visualizzazione interattiva degli oggetti tramite interfaccia web realizzata con ThymeLeaf
   1. Realizzazione per entrambe le entità delle operazioni di base (CRUD) + creazione/eliminazione della relazione MANY-TO-MANY tra gli oggetti
       1. READ (GET) di studenti, corsi, studenti iscritti ad un corso, corsi freqeuntati da uno studente
       1. CREATE (POST) di uno studente, un corso, assegnazione di uno studente ad un corso, assegnazione di un corso ad uno studente
       1. UPDATE (PUT) di uno studente, di un corso
       1. DELETE (DELETE) di uno studente, di un corso, di tutti gli studenti, di tutti i corsi, rimozione di uno studente da un corso, rimozione di un corso da uno studente
   1. Realizzazione di tutte le pagine .html (all'interno della cartella templates) che permettono la visualizzazione e l'esecuzione delle operazioni appena descrite tramite interfaccia web realizzata con ThymeLeaf

# Test
1. Realizzazione dei test che verificano il corretto flusso di esecuzione dei metodi delle classi controller StudenteController e CorsoController