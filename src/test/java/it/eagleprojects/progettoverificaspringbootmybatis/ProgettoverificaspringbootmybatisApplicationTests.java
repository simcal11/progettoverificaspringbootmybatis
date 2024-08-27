package it.eagleprojects.progettoverificaspringbootmybatis;

import it.eagleprojects.progettoverificaspringbootmybatis.controller.CorsoController;
import it.eagleprojects.progettoverificaspringbootmybatis.controller.StudenteController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringBootTest
class ProgettoverificaspringbootmybatisApplicationTests {

	@Autowired
	StudenteController studenteController;

	@Autowired
	CorsoController corsoController;

	@Test
	void contextLoadsStudenteController() {
		Assertions.assertThat(studenteController).isNotNull();
	}

	@Test
	void contextLoadsCorsoController() {
		Assertions.assertThat(corsoController).isNotNull();
	}



}
