package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "articulop")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticuloP {

    @EmbeddedId
    private ArticuloPId id;

    @Column(name = "fechahasta")
    private String fechaHasta;

    @Column(name = "importea")
    private Double importeA;

    @Column(name = "importeb")
    private Double importeB;

    @Column(name = "importec")
    private Double importeC;

    public ArticuloPId getId() {
        return id;
    }

    public void setId(ArticuloPId id) {
        this.id = id;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Double getImporteA() {
        return importeA;
    }

    public void setImporteA(Double importeA) {
        this.importeA = importeA;
    }

    public Double getImporteB() {
        return importeB;
    }

    public void setImporteB(Double importeB) {
        this.importeB = importeB;
    }

    public Double getImporteC() {
        return importeC;
    }

    public void setImporteC(Double importeC) {
        this.importeC = importeC;
    }

}