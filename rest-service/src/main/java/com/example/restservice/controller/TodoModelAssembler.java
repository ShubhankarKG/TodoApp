package com.example.restservice.controller;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.restservice.model.Todo;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class TodoModelAssembler implements RepresentationModelAssembler<Todo, EntityModel<Todo>> {
    @Override
    public EntityModel<Todo> toModel(Todo entity) {
        return EntityModel.of(
            entity,
            linkTo(methodOn(TodoController.class).getTodo(entity.getId())).withSelfRel(),
            linkTo(methodOn(TodoController.class).allTodos()).withRel("todos")
        );
    }
}
