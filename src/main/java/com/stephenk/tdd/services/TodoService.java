package com.stephenk.tdd.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.stephenk.tdd.models.Todo;

public interface TodoService {
    Todo save(Todo todo);

    Page<Todo> getTodos(Pageable pageable);

    void delete(long id);

    Optional<Todo> getTodoById(long id);
}
