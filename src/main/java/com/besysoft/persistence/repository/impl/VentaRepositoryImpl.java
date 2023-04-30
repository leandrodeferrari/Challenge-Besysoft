package com.besysoft.persistence.repository.impl;

import com.besysoft.domain.Venta;
import com.besysoft.persistence.repository.ICrudRepository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class VentaRepositoryImpl implements ICrudRepository<Venta> {

    private final EntityManager em;

    public VentaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Venta> listar() {
        return this.em
                .createQuery("SELECT v FROM Venta v", Venta.class)
                .getResultList();
    }

    @Override
    public void guardar(Venta venta) {
        this.em.persist(venta);
    }

    @Override
    public Venta buscarPorId(Object id) {
        return this.em.find(Venta.class, id);
    }

}
