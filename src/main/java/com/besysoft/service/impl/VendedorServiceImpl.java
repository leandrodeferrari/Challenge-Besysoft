package com.besysoft.service.impl;

import com.besysoft.domain.Vendedor;
import com.besysoft.exception.CrudRepositoryException;
import com.besysoft.persistence.repository.ICrudRepository;
import com.besysoft.persistence.repository.impl.VendedorRepositoryImpl;
import com.besysoft.service.IVendedorService;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class VendedorServiceImpl implements IVendedorService {

    private final EntityManager em;
    private final ICrudRepository<Vendedor> vendedorRepository;

    public VendedorServiceImpl(EntityManager em) {
        this.em = em;
        this.vendedorRepository = new VendedorRepositoryImpl(this.em);
    }

    @Override
    public List<Vendedor> obtenerTodos() {
        try {
            return this.vendedorRepository.listar();
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al listar los vendedores");
        }
    }

    @Override
    public void crear(Vendedor vendedor) {
        if(vendedor == null){
            throw new NullPointerException("El vendedor no puede ser nulo");
        }

        try {
            this.em.getTransaction().begin();
            this.vendedorRepository.guardar(vendedor);
            this.em.getTransaction().commit();
        } catch (CrudRepositoryException ex){
            this.em.getTransaction().rollback();
            throw new CrudRepositoryException("Ocurrió un problema, al registrar al vendedor");
        }
    }

    @Override
    public Optional<Vendedor> buscarPorCodigo(String codigo) {
        if(codigo == null){
            throw new NullPointerException("El codigo no puede ser nulo");
        }

        try {
            return Optional.ofNullable(this.vendedorRepository.buscarPorId(codigo));
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al buscar vendedor por código");
        }
    }

}
