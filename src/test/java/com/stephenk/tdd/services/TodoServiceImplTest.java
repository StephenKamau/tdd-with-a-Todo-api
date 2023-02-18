package com.stephenk.tdd.services;

import com.stephenk.tdd.models.Todo;
import com.stephenk.tdd.repositories.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {


    @Mock
    private TodoRepository todoRepository;

    private TodoServiceImpl todoService;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todoService = new TodoServiceImpl(todoRepository);
        todo = new Todo();
        todo.setId(1);
        todo.setTitle("service test");
        todo.setBody("service body");
        todo.setComplete(false);
    }

    @Test
    void save() {
        given(todoRepository.save(todo)).willReturn(todo);
        Todo savedTodo = todoService.save(todo);
        verify(todoRepository, times(1)).save(any(Todo.class));
        assertEquals(todo, savedTodo);
        verifyNoMoreInteractions(todoRepository);
    }

    @Test
    void getTodos() {
        this.todoService.getTodos(Pageable.ofSize(1));
        verify(todoRepository).findAll(Pageable.ofSize(1));
        verify(todoRepository, times(1)).findAll(Pageable.ofSize(1));
    }

    @Test
    void delete() {
        willDoNothing().given(todoRepository).deleteById(todo.getId());
        todoService.delete(todo.getId());
        verify(todoRepository, times(1)).deleteById(todo.getId());
    }

    @Test
    void getTodoById() {
        given(todoRepository.save(todo)).willReturn(todo);
        todoService.save(todo);
        given(todoRepository.findById(todo.getId())).willReturn(Optional.of(todo));
        assertTrue(todoService.getTodoById(todo.getId()).isPresent());
    }
}