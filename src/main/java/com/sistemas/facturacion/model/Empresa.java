package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Empresa {

    @Id
    @Column(name = "codigo")
    private short codigo;

    @Column(name = "razonsocial")
    private String razonSocial;

    @Column(name = "domicilio")
    private String domicilio;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "codigopostal")
    private String codigoPostal;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "pais")
    private String pais;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "cuit")
    private String cuit;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "rnos")
    private String rnos;

    @Column(name = "email")
    private String email;

    @Column(name = "web")
    private String web;

    @Column(name = "comprobantes")
    private String comprobantes;

    @Column(name = "concepto")
    private int concepto;

    @Column(name = "sitiva")
    private int situacionIva;

    @Column(name = "puntoventa")
    private String puntoVenta;

    public short getCodigo() {
        return codigo;
    }

    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRnos() {
        return rnos;
    }

    public void setRnos(String rnos) {
        this.rnos = rnos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(String comprobantes) {
        this.comprobantes = comprobantes;
    }

    public Integer getConcepto() {
        return concepto;
    }

    public void setConcepto(Integer concepto) {
        this.concepto = concepto;
    }

    public int getSituacionIva() {
        return situacionIva;
    }

    public void setSituacionIva(int situacionIva) {
        this.situacionIva = situacionIva;
    }

    public String getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }
}
