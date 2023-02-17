package com.stephenk.tdd.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import com.stephenk.tdd.models.Todo;

import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @AfterEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void itShouldSaveTodo() {
        Todo todo = new Todo();
        todo.setTitle("test");
        todo.setBody("test body");
        todo.setComplete(false);
        Todo savedTodo = todoRepository.save(todo);
        assertEquals(todo, savedTodo);
    }

    @Test
    void itShouldReturnPageListTodos() {
        Todo todo = new Todo(1, "test", "test body", false);
        todoRepository.save(todo);
        Pageable page = PageRequest.of(0, 10);
        Page<Todo> todos = todoRepository.findAll(page);
        assertTrue(todos.getContent().contains(todo));
    }

    @Test
    void itShouldReturnTodoById() {
        Todo todo = new Todo(1, "test", "test body", false);
        todoRepository.save(todo);
        try {
            Todo retrievedTodo = todoRepository.findById(todo.getId()).get();
            assertEquals(todo, retrievedTodo);
        } catch (EntityNotFoundException eNotFoundException) {
            System.out.println(eNotFoundException);
        }
    }

    @Test
    void itShouldDeleteTodoById() {
        Todo todo = new Todo(1, "test", "test body", false);
        todoRepository.save(todo);
        todoRepository.deleteById(todo.getId());
        assertTrue(todoRepository.findById(todo.getId()).isEmpty());
    }
}
