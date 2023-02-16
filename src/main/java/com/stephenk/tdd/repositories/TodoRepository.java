package com.stephenk.tdd.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.stephenk.tdd.models.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {
    Todo save(Todo todo);

    void deleteAll();

    Todo findById(long id);
}
