package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tcondvent")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CondicionVenta {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "indicativo")
    private String indicativo;

    @Column(name = "dias")
    private Integer dias;

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

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(Integer codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
}
