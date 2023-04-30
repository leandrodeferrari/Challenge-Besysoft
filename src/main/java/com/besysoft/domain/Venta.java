package com.besysoft.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CANTIDAD", nullable = false)
    private Integer cantidad;

    @Column(name = "IMPORTE_TOTAL", precision = 19, scale = 2, nullable = false)
    private BigDecimal importeTotal;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_PRODUCTO", nullable = false)
    private Producto producto;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "CODIGO_VENDEDOR", nullable = false)
    private Vendedor vendedor;

    public Venta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Venta {" +
                "id = " + id +
                ", cantidad = " + cantidad +
                ", importeTotal = " + importeTotal +
                ", producto = " + producto +
                ", vendedor = " + vendedor +
                '}';
    }

}
