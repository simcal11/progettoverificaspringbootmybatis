package it.eagleprojects.progettoverificaspringbootmybatis;

import it.eagleprojects.progettoverificaspringbootmybatis.model.Studente;
import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("it.eagleprojects.progettoverificaspringbootmybatis.mapper")
@MappedTypes({Studente.class})
public class
ProgettoverificaspringbootmybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoverificaspringbootmybatisApplication.class, args);
	}

}
