package com.example.restservice.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.restservice.model.Todo;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    
    @GetMapping("/todos")
    public HttpEntity<Todo[]> getTodos() {
        Todo[] todos = new Todo[] {
            new Todo(1, "Learn Spring Boot", "Content 1", 0),
            new Todo(2, "Learn Spring Data REST", "Content 2", 1),
            new Todo(3, "Learn Spring Data Neo4j", "Content 3", 1)
        };
        for (int i=0; i<3; i++) {
            todos[i].add(linkTo(methodOn(TodoController.class).getTodos()).withSelfRel());
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
}
