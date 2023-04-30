package com.besysoft.domain;

import jakarta.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "vendedores")
public class Vendedor implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "NOMBRE", length = 50, nullable = false)
    private String nombre;

    @Column(name = "SUELDO", precision = 19, scale = 2, nullable = false)
    private BigDecimal sueldo;

    public Vendedor() {
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

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    @Override
    public String toString() {
        return "Vendedor {" +
                "codigo = '" + codigo + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", sueldo = " + sueldo +
                '}';
    }

}
