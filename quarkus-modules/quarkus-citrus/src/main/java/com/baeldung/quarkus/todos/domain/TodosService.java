package com.baeldung.quarkus.todos.domain;

import com.baeldung.quarkus.shared.interceptors.FireEvent;
import com.baeldung.quarkus.shared.interceptors.Validated;
import com.baeldung.quarkus.todos.domain.TodoEvents.TodoCreated;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@Validated
@ApplicationScoped
@RequiredArgsConstructor
public class TodosService {

    private final TodosSink sink;

    public Collection<Todo> findAll() {
        return sink.findAll();
    }

    public Optional<Todo> findById(Long id) {
        return sink.findById(id);
    }

    @FireEvent(TodoCreated.class)
    public void add(@Valid Todo todo) {
        this.sink.add(todo);
    }

}
