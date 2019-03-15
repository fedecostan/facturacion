package com.sistemas.facturacion.service.dto;

import java.util.List;

public class FacturaDTO {

    private String fecha;
    private Integer tipoComprobante;
    private String puntoVenta;
    private String sindicato;
    private String afiliado;
    private String situacionesIva;
    private String condicionesVenta;
    private Double bonificacion;
    private String listaPrecio;
    private Double total;
    private String leyenda;
    private List<ArticuloFacturaDTO> articulos;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(Integer tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(String puntoVenta) {
        this.puntoVenta = puntoVenta;
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

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public List<ArticuloFacturaDTO> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<ArticuloFacturaDTO> articulos) {
        this.articulos = articulos;
    }
}
