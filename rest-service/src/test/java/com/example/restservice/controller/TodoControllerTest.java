package com.example.restservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.restservice.model.Todo;
import com.example.restservice.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@WebMvcTest(TodoController.class)
@Import({ TodoModelAssembler.class })
@ActiveProfiles("test")
public class TodoControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TodoRepository todoRepository;

    Todo todo_1 = new Todo("Test title 1", "Test Content 1", 0);
    Todo todo_2 = new Todo("Test title 2", "Test Content 2", 0);
    Todo todo_3 = new Todo("Test title 3", "Test Content 3", 1);
    Todo todo_4 = new Todo("Test title 4", "Test Content 4", 1);

    @Test
    public void getAllTodosTest() throws Exception {
        List<Todo> todos = new ArrayList<>(Arrays.asList(todo_1, todo_2, todo_3, todo_4));
        Mockito.when(todoRepository.findAll()).thenReturn(todos);
        mockMvc.perform(MockMvcRequestBuilders
            .get("/todos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$._embedded.todoList", hasSize(4)))
            .andExpect(jsonPath("$._embedded.todoList[0].title", is(todo_1.getTitle())));
    }

    @Test
    public void getTodoByIdTest() throws Exception {
        Mockito.when(todoRepository.findById(1)).thenReturn(java.util.Optional.of(todo_1));
        mockMvc.perform(MockMvcRequestBuilders
            .get("/todos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.title", is(todo_1.getTitle())));
    }

    @Test
    public void getTodoByIdExceptionTest() throws Exception {
        Mockito.when(todoRepository.findById(100)).thenReturn(java.util.Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
            .get("/todos/100")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.errorMessage", is("Could not find todo 100")));
    }

    @Test
    public void createTodoTest() throws Exception {
        Todo todo = new Todo("Test title", "Test Content", 0);
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/todos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is(todo.getTitle())));
    }

    @Test
    public void updateTodoTest() throws Exception {
        Todo todo = new Todo("Test title", "Test Content", 0);
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);
        mockMvc.perform(MockMvcRequestBuilders
            .put("/todos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(todo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is(todo.getTitle())));
    }

    @Test
    public void updateTodoTest_update() throws Exception {
        Todo updated_todo = new Todo("Updated test title", "Updated test Content", 0);
        Mockito.when(todoRepository.save(updated_todo)).thenReturn(updated_todo);
        mockMvc.perform(MockMvcRequestBuilders
            .put("/todos/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updated_todo)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.title", is(updated_todo.getTitle())))
            .andExpect(jsonPath("$.content", is(updated_todo.getContent())));
    }

    @Test
    public void deleteTodoTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
            .delete("/todos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

}
