package com.besysoft.service;

import com.besysoft.domain.Vendedor;

import java.util.List;
import java.util.Optional;

public interface IVendedorService {

    List<Vendedor> obtenerTodos();
    void crear(Vendedor vendedor);
    Optional<Vendedor> buscarPorCodigo(String codigo);

}
