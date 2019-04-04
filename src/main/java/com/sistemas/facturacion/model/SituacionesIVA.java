package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tsitiva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SituacionesIVA {

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "base")
    private Double base;

    @Column(name = "apertura")
    private Double apertura;

    @Column(name = "cuenta")
    private String cuenta;

    @Column(name = "cuentaapertura")
    private String cuentaApertura;

    @Id
    @Column(name = "codigoafip")
    private Integer codigoAfip;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getBase() {
        return base;
    }

    public void setBase(Double base) {
        this.base = base;
    }

    public Double getApertura() {
        return apertura;
    }

    public void setApertura(Double apertura) {
        this.apertura = apertura;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getCuentaApertura() {
        return cuentaApertura;
    }

    public void setCuentaApertura(String cuentaApertura) {
        this.cuentaApertura = cuentaApertura;
    }

    public Integer getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(Integer codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
}
