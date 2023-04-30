package com.besysoft.service;

import com.besysoft.domain.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> obtenerTodos();
    void crear(Producto producto);
    List<Producto> buscarPorCategoria(String categoria);
    List<Producto> buscarPorNombre(String nombre);
    List<Producto> buscarPorRangoDePrecios(BigDecimal desde, BigDecimal hasta);
    Optional<Producto> buscarPorCodigo(String codigo);

}
