package com.example.customermanagementjpa.service;

import com.example.customermanagementjpa.exception.DuplicateEmailException;

import java.util.Optional;

public interface IGenerateService<T> {
    Iterable<T> findAll();

    void save(T Object) throws DuplicateEmailException;

    Optional<T> findById(Long id);

    void remove(Long id);
}
