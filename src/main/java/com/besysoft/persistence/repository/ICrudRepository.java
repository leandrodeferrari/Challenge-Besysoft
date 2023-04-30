package com.besysoft.persistence.repository;

import java.util.List;

public interface ICrudRepository<T> {

    List<T> listar();
    void guardar(T t);
    T buscarPorId(Object id);

}
