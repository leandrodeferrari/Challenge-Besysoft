package com.besysoft.service;

import com.besysoft.domain.Vendedor;
import com.besysoft.domain.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<Venta> obtenerTodos();
    void crear(Venta venta);
    void calcularComisiones(Vendedor vendedor);
    Optional<Venta> buscarPorId(Long id);
    List<Venta> buscarPorVendedor(Vendedor vendedor);

}
