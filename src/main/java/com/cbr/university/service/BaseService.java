package com.cbr.university.service;

import java.util.List;

public interface BaseService<T> {

    T create(T t);

    T update(T t);

    void deleteById(int id);

    List<T> getAll();

    T getById(int id);

    boolean existsById(int id);

}
