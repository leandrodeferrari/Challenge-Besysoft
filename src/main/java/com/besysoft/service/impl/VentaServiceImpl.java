package com.besysoft.service.impl;

import com.besysoft.domain.Vendedor;
import com.besysoft.domain.Venta;
import com.besysoft.exception.CrudRepositoryException;
import com.besysoft.persistence.repository.ICrudRepository;
import com.besysoft.persistence.repository.impl.VentaRepositoryImpl;
import com.besysoft.service.IVentaService;

import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VentaServiceImpl implements IVentaService {

    private final EntityManager em;
    private final ICrudRepository<Venta> ventaRepository;

    public VentaServiceImpl(EntityManager em) {
        this.em = em;
        this.ventaRepository = new VentaRepositoryImpl(this.em);
    }

    @Override
    public List<Venta> obtenerTodos() {
        try {
            return this.ventaRepository.listar();
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema, al listar las ventas");
        }
    }

    @Override
    public void crear(Venta venta) {
        if(venta == null){
            throw new NullPointerException("La venta no puede ser nula");
        }

        BigDecimal importeTotal = venta.getProducto()
                .getPrecio().multiply(new BigDecimal(venta.getCantidad().toString()));
        venta.setImporteTotal(importeTotal);
        try {
            this.em.getTransaction().begin();
            this.ventaRepository.guardar(venta);
            this.em.getTransaction().commit();
        } catch (CrudRepositoryException ex){
            this.em.getTransaction().rollback();
            throw new CrudRepositoryException("Ocurrió un problema, al registrar la venta");
        }
    }

    @Override
    public void calcularComisiones(Vendedor vendedor) {
        if(vendedor == null){
            throw new NullPointerException("El vendedor no puede ser nulo");
        }

        List<Venta> ventas = buscarPorVendedor(vendedor);

        if(ventas.isEmpty()){
            System.out.println("El vendedor no tiene ventas");
        }

        if(ventas.size() == 1){
            Venta venta = ventas.get(0);
            if(venta.getCantidad() <= 2){
                BigDecimal comision = venta.getImporteTotal().multiply(new BigDecimal("0.05"));
                System.out.println("Comisión: " + comision);
            } else {
                BigDecimal comision = venta.getImporteTotal().multiply(new BigDecimal("0.10"));
                System.out.println("Comisión: " + comision);
            }
        }

        if(ventas.size() == 2){
            int cantidades = ventas.get(0).getCantidad() + ventas.get(1).getCantidad();
            if(cantidades == 2){
                List<BigDecimal> totales = ventas.stream()
                        .map(Venta::getImporteTotal)
                        .toList();
                BigDecimal total = sumarValores(totales);
                BigDecimal comision = total.multiply(new BigDecimal("0.05"));
                System.out.println("Comisiones: " + comision);
            } else {
                List<BigDecimal> totales = ventas.stream()
                        .map(Venta::getImporteTotal)
                        .toList();
                BigDecimal total = sumarValores(totales);
                BigDecimal comision = total.multiply(new BigDecimal("0.10"));
                System.out.println("Comisiones: " + comision);
            }
        }

        if(ventas.size() > 2){
            List<BigDecimal> totales = ventas.stream()
                    .map(Venta::getImporteTotal)
                    .toList();
            BigDecimal total = sumarValores(totales);
            BigDecimal comision = total.multiply(new BigDecimal("0.10"));
            System.out.println("Comisiones: " + comision);
        }
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        if(id == null){
            throw new NullPointerException("El ID no puede ser nulo");
        }

        return Optional.ofNullable(this.ventaRepository.buscarPorId(id));
    }

    @Override
    public List<Venta> buscarPorVendedor(Vendedor vendedor) {
        if(vendedor == null){
            throw new NullPointerException("El vendedor no puede ser nulo");
        }

        try {
            List<Venta> ventas = this.ventaRepository.listar();
            return ventas.stream()
                    .filter(v -> v.getVendedor().getCodigo().equals(vendedor.getCodigo()))
                    .collect(Collectors.toList());
        } catch (CrudRepositoryException ex){
            throw new CrudRepositoryException("Ocurrió un problema al buscar ventas, por vendedor");
        }
    }

    private BigDecimal sumarValores(List<BigDecimal> valores) {
        if(valores == null){
            throw new NullPointerException("Los valores no pueden ser nulos");
        }

        BigDecimal resultado = BigDecimal.ZERO;

        for (BigDecimal valor : valores) {
            if(valor!=null){
                resultado = resultado.add(valor);
            }

        }

        return resultado;

    }

}
