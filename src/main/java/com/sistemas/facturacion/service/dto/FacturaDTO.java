package com.sistemas.facturacion.service.dto;

import java.util.List;

public class FacturaDTO {

    private String fecha;
    private int tipoComprobante;
    private int puntoVenta;
    private long numeroComprobante;
    private String sindicato;
    private String afiliado;
    private Double situacionesIva;
    private String condicionesVenta;
    private Double bonificacion;
    private String listaPrecio;
    private Double total;
    private List<ArticuloFacturaDTO> articulos;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(int tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public int getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(int puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public long getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(long numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getSindicato() {
        return sindicato;
    }

    public void setSindicato(String sindicato) {
        this.sindicato = sindicato;
    }

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public Double getSituacionesIva() {
        return situacionesIva;
    }

    public void setSituacionesIva(Double situacionesIva) {
        this.situacionesIva = situacionesIva;
    }

    public String getCondicionesVenta() {
        return condicionesVenta;
    }

    public void setCondicionesVenta(String condicionesVenta) {
        this.condicionesVenta = condicionesVenta;
    }

    public Double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(String listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<ArticuloFacturaDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloFacturaDTO> articulos) {
        this.articulos = articulos;
    }
}
