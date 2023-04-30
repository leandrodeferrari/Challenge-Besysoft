package com.besysoft.persistence.repository.impl;

import com.besysoft.domain.Vendedor;
import com.besysoft.persistence.repository.ICrudRepository;

import jakarta.persistence.EntityManager;

import java.util.List;

public class VendedorRepositoryImpl implements ICrudRepository<Vendedor> {

    private final EntityManager em;

    public VendedorRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Vendedor> listar() {
        return this.em
                .createQuery("SELECT v FROM Vendedor v", Vendedor.class)
                .getResultList();
    }

    @Override
    public void guardar(Vendedor vendedor) {
        this.em.persist(vendedor);
    }

    @Override
    public Vendedor buscarPorId(Object id) {
        return this.em.find(Vendedor.class, id);
    }

}
