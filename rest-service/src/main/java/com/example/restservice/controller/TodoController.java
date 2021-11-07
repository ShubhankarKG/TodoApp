package com.example.restservice.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import com.example.restservice.exception.TodoNotFoundException;
import com.example.restservice.model.Todo;
import com.example.restservice.repository.TodoRepository;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
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
    private final TodoModelAssembler assembler;
    
    TodoController(TodoRepository repository, TodoModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/todos")
    CollectionModel<EntityModel<Todo>> allTodos() {
        List<EntityModel<Todo>> todos = repository.findAll().stream().
            map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(
            todos,
            linkTo(methodOn(TodoController.class).allTodos()).withSelfRel()
        );
    }

    @GetMapping("/todos/{id}")
    EntityModel<Todo> getTodo(@PathVariable int id) {
        Todo todo = repository.findById(id)
                .orElseThrow(() -> new TodoNotFoundException(id));
        return assembler.toModel(todo);
    }

    @PostMapping("/todos")
    ResponseEntity<?> newTodo(@RequestBody Todo newTodo) {
        EntityModel<Todo> todo = assembler.toModel(repository.save(newTodo));
        return ResponseEntity.created(todo.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(todo);
    }

    @PutMapping("/todos/{id}")
    ResponseEntity<?> updateTodo(@RequestBody Todo newTodo, @PathVariable int id) {
        Todo updatedTodo = repository.findById(id)
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
        EntityModel<Todo> entityModel = assembler.toModel(updatedTodo);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/todos/{id}")
    ResponseEntity<?> deleteTodo(@PathVariable int id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
