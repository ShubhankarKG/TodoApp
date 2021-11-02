package com.example.restservice;

import com.example.restservice.model.Todo;
import com.example.restservice.repository.TodoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    // @Bean
    CommandLineRunner initDatabase(TodoRepository todoRepository) {
        return args -> {
            todoRepository.save(new Todo("Buy milk", "Content 1", 0));
            todoRepository.save(new Todo("Buy bread", "Content 2", 0));
            todoRepository.save(new Todo("Buy coffee", "Content 3", 0));
            todoRepository.save(new Todo("Buy tea", "Content 4", 0));
            todoRepository.save(new Todo("Buy chocolate", "Content 5", 0));
            todoRepository.save(new Todo("Buy cookies", "Content 8", 1));
            todoRepository.save(new Todo("Buy milk", "Content 6", 1));
            todoRepository.save(new Todo("Buy bread", "Content 7", 1));
            logger.info("Preloaded " + todoRepository.findAll().size() + " todos");
        };
    }

}
