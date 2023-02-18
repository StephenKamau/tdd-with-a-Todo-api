package com.stephenk.tdd.services;

import com.stephenk.tdd.models.Todo;
import com.stephenk.tdd.repositories.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Override
    public Todo save(Todo todo) {
        // Save todo
        Todo savedTodo = todoRepository.save(todo);
        StringBuilder logMessage = new StringBuilder();
        logMessage
                .append(System.lineSeparator())
                .append("******************************")
                .append(System.lineSeparator())
                .append("Saved todo:")
                .append(System.lineSeparator())
                .append(savedTodo)
                .append(System.lineSeparator())
                .append("******************************");
        logger.info(logMessage.toString());
        return savedTodo;
    }

    @Override
    public Page<Todo> getTodos(Pageable pageable) {
        // fetch all todos
        return todoRepository.findAll(pageable);
    }

    @Override
    public void delete(long id) {
        // delete todo by id
        todoRepository.deleteById(id);
        StringBuilder logMessage = new StringBuilder();
        logMessage
                .append(System.lineSeparator())
                .append("******************************")
                .append(System.lineSeparator())
                .append("Deleted todo with ID: ")
                .append(System.lineSeparator())
                .append(id)
                .append(System.lineSeparator())
                .append("******************************");
        logger.info(logMessage.toString());
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        // fetch todo by id
        Optional<Todo> todo = todoRepository.findById(id);
        StringBuilder logMessage = new StringBuilder();
        logMessage
                .append(System.lineSeparator())
                .append("******************************")
                .append(System.lineSeparator())
                .append("Fetched todo with ID: ")
                .append(id)
                .append(System.lineSeparator())
                .append(todo)
                .append(System.lineSeparator())
                .append("******************************");
        logger.info(logMessage.toString());
        return todo;
    }

}
