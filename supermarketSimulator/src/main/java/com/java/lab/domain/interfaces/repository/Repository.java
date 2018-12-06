package com.java.lab.domain.interfaces.repository;

import com.java.lab.domain.exception.RepositoryException;

import java.util.List;

public interface Repository<T> {
    void add(T item) throws RepositoryException;

    void update(T item);

    void remove(T item) throws RepositoryException;

    T find(Integer id);

    List<T> findAll();
}
