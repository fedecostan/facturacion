package com.sistemas.facturacion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MovimientoClienteId implements Serializable {

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "codicomprobante")
    private String codigoComprobante;

    @Column(name = "nrocomprobante")
    private String nroComprobante;

    @Column(name = "tipocomprobante")
    private String tipoComprobante;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCodigoComprobante() {
        return codigoComprobante;
    }

    public void setCodigoComprobante(String codigoComprobante) {
        this.codigoComprobante = codigoComprobante;
    }

    public String getNroComprobante() {
        return nroComprobante;
    }

    public void setNroComprobante(String nroComprobante) {
        this.nroComprobante = nroComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovimientoClienteId)) return false;
        MovimientoClienteId that = (MovimientoClienteId) o;
        return Objects.equals(getFecha(), that.getFecha()) &&
                Objects.equals(getCodigoComprobante(), that.getCodigoComprobante()) &&
                Objects.equals(getNroComprobante(), that.getNroComprobante()) &&
                Objects.equals(getTipoComprobante(), that.getTipoComprobante());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getCodigoComprobante(), getNroComprobante(), getTipoComprobante());
    }
}