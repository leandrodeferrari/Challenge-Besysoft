package com.besysoft.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOMBRE", length = 150, nullable = false)
    private String nombre;

    @Column(name = "PRECIO", precision = 19, scale = 2, nullable = false)
    private BigDecimal precio;

    @Column(name = "CATEGORIA", length = 50, nullable = false)
    private String categoria;

    public Producto() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto {" +
                "codigo = '" + codigo + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", precio = " + precio +
                ", categoria = '" + categoria + '\'' +
                '}';
    }

}
