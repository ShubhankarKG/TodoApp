# TODO app

This is just a regular Todo App. The aim is to learn the Tech stack.

[![codecov](https://codecov.io/gh/ShubhankarKG/TodoApp/branch/master/graph/badge.svg?token=G1F6YFCKZD)](https://codecov.io/gh/ShubhankarKG/TodoApp)

## Tech stack

1. Frontend - Angular
2. Backend - Spring Boot
3. Database - PostgreSQL

## Flow of the app

1. Anyone uses the Todo app to create todos and store them.
2. Store that anyone as user and connect that user to todos.
3. Use that user to login, as well as allow them to only register and do todos. In the process, add spring security as well.

## Models

1. Todo

```json
{
    "id": 1,
    "title" : "Todo title",
    "description": "Todo description",
    "status": "Finished | Pending",
    "user_id": 123
}
```

2. User

```json
{
    "id": 123,
    "name": "User name",
    "email": "Email ID",
    "password": "hash(password)",
}
```
