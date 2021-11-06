package com.example.restservice;

import com.example.restservice.controller.TodoController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RestServiceApplicationTests {

	@Autowired TodoController todoController;

	@Test
	void contextLoads() {
		assertThat(todoController).isNotNull();
	}

}
