package com.example.restservice;

import com.example.restservice.controller.TodoController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestServiceApplicationTests {

	@Autowired TodoController todoController;

	@Test
	void contextLoads() {
		assertThat(todoController).isNotNull();
	}

}
