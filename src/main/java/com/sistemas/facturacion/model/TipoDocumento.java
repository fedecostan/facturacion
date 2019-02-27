package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ttipodocu")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoDocumento {

    @Id
    @Column(name = "codigo")
    private String codigo;

    @Column(name = "descripcion")
    private String descripcion;

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

    public Integer getCodigoAfip() {
        return codigoAfip;
    }

    public void setCodigoAfip(Integer codigoAfip) {
        this.codigoAfip = codigoAfip;
    }
}
