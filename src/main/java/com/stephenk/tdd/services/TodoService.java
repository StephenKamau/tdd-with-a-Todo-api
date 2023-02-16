package com.stephenk.tdd.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stephenk.tdd.models.Todo;

public interface TodoService {
    Todo save(Todo todo);

    Page<Todo> getTodos(Pageable pageable);

    Todo delete(long id);

    Todo getTodoById(long id);
}
