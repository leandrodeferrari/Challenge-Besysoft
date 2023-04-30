package com.besysoft.service.impl;

import com.besysoft.domain.Producto;
import com.besysoft.exception.CrudRepositoryException;
import com.besysoft.persistence.repository.ICrudRepository;
import com.besysoft.persistence.repository.impl.ProductoRepositoryImpl;
import com.besysoft.service.IProductoService;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoServiceImpl implements IProductoService {

    private final EntityManager em;
    private final ICrudRepository<Producto> productoRepository;

    public ProductoServiceImpl(EntityManager em) {
        this.em = em;
        this.productoRepository = new ProductoRepositoryImpl(this.em);
    }

    @Override
    public List<Producto> obtenerTodos() {
        try {
            return this.productoRepository.listar();
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al listar los productos");
        }
    }

    @Override
    public void crear(Producto producto) {
        if(producto == null){
            throw new NullPointerException("El producto no puede ser nulo");
        }

        try {
            this.em.getTransaction().begin();
            this.productoRepository.guardar(producto);
            this.em.getTransaction().commit();
        } catch (CrudRepositoryException ex){
            this.em.getTransaction().rollback();
            throw new CrudRepositoryException("Ocurrió un problema, al registrar el producto");
        }
    }

    @Override
    public List<Producto> buscarPorCategoria(String categoria) {
        if(categoria == null){
            throw new NullPointerException("El categoría no puede ser nula");
        }

        try {
            List<Producto> productos = obtenerTodos();
            return productos.stream()
                    .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al buscar por categoría");
        }
    }

    @Override
    public List<Producto> buscarPorNombre(String nombre) {
        if(nombre == null){
            throw new NullPointerException("El nombre no puede ser nulo");
        }

        try {
            List<Producto> productos = obtenerTodos();
            return productos.stream()
                    .filter(p -> p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al buscar por nombre");
        }
    }

    @Override
    public List<Producto> buscarPorRangoDePrecios(BigDecimal desde, BigDecimal hasta) {
        if(desde == null){
            throw new NullPointerException("El precio mínimo no puede ser nulo");
        }

        if(hasta == null){
            throw new NullPointerException("El precio máximo no puede ser nulo");
        }

        try {
            List<Producto> productos = obtenerTodos();
            return productos.stream()
                    .filter(p -> p.getPrecio().compareTo(desde) >= 0 && p.getPrecio().compareTo(hasta) <= 0)
                    .collect(Collectors.toList());
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al buscar por rango de precios");
        }
    }

    @Override
    public Optional<Producto> buscarPorCodigo(String codigo) {
        if(codigo == null){
            throw new NullPointerException("El código no puede ser nulo");
        }

        try {
            return Optional.ofNullable(this.productoRepository.buscarPorId(codigo));
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al buscar producto por código");
        }
    }

}
