package com.sistemas.facturacion.service.dto;

import java.util.List;

public class FacturaDTO {

    private String fecha;
    private String tipoComprobante;
    private String puntoVenta;
    private String numeroComprobante;
    private String sindicato;
    private String afiliado;
    private String situacionesIva;
    private String condicionesVenta;
    private String bonificacion;
    private String listaPrecio;
    private String total;
    private List<ArticuloFacturaDTO> articulos;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
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

    public String getSituacionesIva() {
        return situacionesIva;
    }

    public void setSituacionesIva(String situacionesIva) {
        this.situacionesIva = situacionesIva;
    }

    public String getCondicionesVenta() {
        return condicionesVenta;
    }

    public void setCondicionesVenta(String condicionesVenta) {
        this.condicionesVenta = condicionesVenta;
    }

    public String getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(String bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(String listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<ArticuloFacturaDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloFacturaDTO> articulos) {
        this.articulos = articulos;
    }
}
