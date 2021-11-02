package com.example.restservice.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import com.example.restservice.exception.TodoNotFoundException;
import com.example.restservice.model.Todo;
import com.example.restservice.repository.TodoRepository;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final TodoRepository repository;
    
    TodoController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/todos")
    List<Todo> allTodos() {
        return repository.findAll();
    }

    @GetMapping("/todos/{id}")
    Todo getTodo(@PathVariable int id) {
        return repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
    }

    @PostMapping("/todos")
    Todo newTodo(@RequestBody Todo newTodo) {
        return repository.save(newTodo);
    }

    @PutMapping("/todos/{id}")
    Todo updateRodo(@RequestBody Todo newTodo, @PathVariable int id) {

        return repository.findById(id)
                .map(todo -> {
                    todo.setTitle(newTodo.getTitle());
                    todo.setContent(newTodo.getContent());
                    todo.setStatus(newTodo.getStatus());
                    return repository.save(todo);
                })
                .orElseGet(() -> {
                    newTodo.setId(id);
                    return repository.save(newTodo);
                });
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodo(@PathVariable int id) {
        repository.deleteById(id);
    }
}
