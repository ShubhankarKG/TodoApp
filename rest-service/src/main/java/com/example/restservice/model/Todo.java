package com.example.restservice.model;

import org.springframework.hateoas.RepresentationModel;

public class Todo extends RepresentationModel<Todo> {
    private int id;
    private String title;
    private String content;
    private int status;
    
    public Todo() {
    }

    public Todo(int id, String title, String content, int status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
