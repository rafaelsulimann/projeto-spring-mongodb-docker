package br.com.sulimann.ApplicationTests;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import br.com.sulimann.Projeto.Application;

@SpringBootTest(classes = Application.class)
@ContextConfiguration
class ApplicationTest {

	@Test
	void contextLoads() {
	}

}
