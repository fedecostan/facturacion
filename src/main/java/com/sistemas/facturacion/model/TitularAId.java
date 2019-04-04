package com.sistemas.facturacion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TitularAId implements Serializable {

    @Column(name = "nroregistro")
    private String nroRegistro;

    @Column(name = "fechaalta")
    private String fechaAlta;

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitularAId)) return false;
        TitularAId that = (TitularAId) o;
        return Objects.equals(getNroRegistro(), that.getNroRegistro()) &&
                Objects.equals(getFechaAlta(), that.getFechaAlta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNroRegistro(), getFechaAlta());
    }
}