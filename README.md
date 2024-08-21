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
    1. Creazione del package model con Studente.java
    1. Creazione del package mapper con il mapper StudenteMapper.java
    1. Creazione del package controller per il Controller di Studente
    1. Realizzazione delle operazioni di base
        1. READ (GET) di studenti o di uno studente dato un id
        1. CREATE (POST) di uno studente
        1. UPDATE (PUT) di uno studente
        1. DELETE (DELETE) di uno studente o di tutti gli studenti
