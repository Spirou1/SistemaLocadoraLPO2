package com.suaempresa.locadora.model.dao;

import java.util.List;

public interface Dao<T> {
    void insert(T entity);
    
    List<T> getAll();
    
    T getById(long id);

   
    void update(T entity);

   
    void delete(T entity);
    
    void deleteAll();
}