package com.cbr.university.service;

import java.util.List;

public interface BaseService<T> {

    T create(T t);

    T update(T t);

    void delete(T t);

    List<T> getAll();

    T getById(int id);

    boolean existsById(int id);

}
