package com.sistemas.facturacion.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticuloPId implements Serializable {

    @Column(name = "rubroarticulo")
    private String rubroArticulo;

    @Column(name = "fechadesde")
    private String fechaDesde;

    public String getRubroArticulo() {
        return rubroArticulo;
    }

    public void setRubroArticulo(String rubroArticulo) {
        this.rubroArticulo = rubroArticulo;
    }

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticuloPId)) return false;
        ArticuloPId that = (ArticuloPId) o;
        return Objects.equals(getRubroArticulo(), that.getRubroArticulo()) &&
                Objects.equals(getFechaDesde(), that.getFechaDesde());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRubroArticulo(), getFechaDesde());
    }
}