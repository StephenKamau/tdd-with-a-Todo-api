package com.stephenk.tdd.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.stephenk.tdd.models.Todo;
import com.stephenk.tdd.repositories.TodoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    private Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Override
    public Todo save(Todo todo) {
        // Save todo
        Todo savedTodo = todoRepository.save(todo);
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("*********Saved todo********")
                .append(System.lineSeparator())
                .append(savedTodo)
                .append(System.lineSeparator())
                .append("*********Saved todo********");
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
        logMessage.append("*********Deleted todo with Id:********")
                .append(System.lineSeparator())
                .append(id)
                .append(System.lineSeparator())
                .append("*********Deleted todo with Id:********");
        logger.info(logMessage.toString());
    }

    @Override
    public Todo getTodoById(long id) {
        // fetch todo by id
        Todo todo = todoRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("*********Fetched todo with Id:********")
                .append(System.lineSeparator())
                .append(todo)
                .append(System.lineSeparator())
                .append("*********Fetched todo with Id:********");
        logger.info(logMessage.toString());
        return todo;
    }

}
