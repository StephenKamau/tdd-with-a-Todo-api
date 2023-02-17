package com.stephenk.tdd.repositories;


import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.stephenk.tdd.models.Todo;


public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    Todo save(Todo todo);

    void deleteAll();

    Optional<Todo> findById(long id);

    void deleteById(long id);

}
