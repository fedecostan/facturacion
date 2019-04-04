package com.sistemas.facturacion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FamiliarId implements Serializable {

    @Column(name = "nroregistro")
    private String nroRegistro;

    @Column(name = "orden")
    private int orden;

    public String getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(String nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamiliarId)) return false;
        FamiliarId that = (FamiliarId) o;
        return Objects.equals(getNroRegistro(), that.getNroRegistro()) &&
                Objects.equals(getOrden(), that.getOrden());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNroRegistro(), getOrden());
    }
}