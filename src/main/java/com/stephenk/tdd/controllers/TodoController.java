package com.stephenk.tdd.controllers;

import com.stephenk.tdd.models.Todo;
import com.stephenk.tdd.services.TodoServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final TodoServiceImpl todoService;

    public TodoController(TodoServiceImpl todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public Page<Todo> fetchTodos(@RequestParam int page, @RequestParam int size) {
        return todoService.getTodos(PageRequest.of(page, size));
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        if (todo.getId() != 0L) {
            todo.setId(0L);
        }
        return todoService.save(todo);
    }
}
