package com.besysoft.persistence.repository.impl;

import com.besysoft.domain.Producto;
import com.besysoft.persistence.repository.ICrudRepository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductoRepositoryImpl implements ICrudRepository<Producto> {

    private final EntityManager em;

    public ProductoRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Producto> listar() {
        return this.em
                .createQuery("SELECT p FROM Producto p", Producto.class)
                .getResultList();
    }

    @Override
    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    @Override
    public Producto buscarPorId(Object id) {
        return this.em.find(Producto.class, id);
    }

}
