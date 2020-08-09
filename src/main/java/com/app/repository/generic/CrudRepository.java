package com.app.repository.generic;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CrudRepository<T, ID> {
    Optional<T> add(T item);
    Optional<T> update(T item);
    Optional<T> findById(ID id);
    Optional<T> findLast();
    List<T> findAll();
    List<T> findAllById(List<ID> ids);
    Boolean deleteById(ID id);
}
