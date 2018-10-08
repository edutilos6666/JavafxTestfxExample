package com.edutilos.com.edutilos.dao;

import java.util.List;

public interface DAO<S,T> {
    void create(S s);
    void save(T t, S s);
    void delete(T t);
    S find(T t);
    List<S> findAll();
}
