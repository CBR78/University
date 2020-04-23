package com.cbr.university.spring.dao;

import java.util.List;

public interface BaseDao<T> {

    boolean create(T t);

    boolean update(T t);

    boolean delete(T t);

    List<T> getAll();

    T getById(int id);
}
