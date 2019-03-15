package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articuloc")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ArticuloC {

    @Id
    @Column(name = "rubroarticulo")
    private String rubroArticulo;

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "articulo")
    private String articulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "stock")
    private int stock;

    @Column(name = "puntorepo")
    private int puntoRepo;

    @Column(name = "codiva")
    private String codIva;

    @Column(name = "codmoneda")
    private String codMoneda;

    @Column(name = "codib")
    private String codIb;

    @Column(name = "precio1")
    private Double precio1;

    @Column(name = "precio2")
    private Double precio2;

    @Column(name = "precio3")
    private Double precio3;

    @Column(name = "impintip")
    private String impIntIp;

    @Column(name = "impinternos")
    private Double impInternos;

    @Column(name = "usualta")
    private String usuarioAlta;

    @Column(name = "fecalta")
    private String fechaAlta;

    @Column(name = "usumodi")
    private String usuarioModificacion;

    @Column(name = "fecmodi")
    private String fechaModificacion;

    @Column(name = "codbarra")
    private String codigoBarra;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "articuloC", targetEntity = ArticuloP.class, cascade=CascadeType.ALL)
    private List<ArticuloP> articuloPList;

    public String getRubroArticulo() {
        return rubroArticulo;
    }

    public void setRubroArticulo(String rubroArticulo) {
        this.rubroArticulo = rubroArticulo;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPuntoRepo() {
        return puntoRepo;
    }

    public void setPuntoRepo(int puntoRepo) {
        this.puntoRepo = puntoRepo;
    }

    public String getCodIva() {
        return codIva;
    }

    public void setCodIva(String codIva) {
        this.codIva = codIva;
    }

    public String getCodMoneda() {
        return codMoneda;
    }

    public void setCodMoneda(String codMoneda) {
        this.codMoneda = codMoneda;
    }

    public String getCodIb() {
        return codIb;
    }

    public void setCodIb(String codIb) {
        this.codIb = codIb;
    }

    public Double getPrecio1() {
        return precio1;
    }

    public void setPrecio1(Double precio1) {
        this.precio1 = precio1;
    }

    public Double getPrecio2() {
        return precio2;
    }

    public void setPrecio2(Double precio2) {
        this.precio2 = precio2;
    }

    public Double getPrecio3() {
        return precio3;
    }

    public void setPrecio3(Double precio3) {
        this.precio3 = precio3;
    }

    public String getImpIntIp() {
        return impIntIp;
    }

    public void setImpIntIp(String impIntIp) {
        this.impIntIp = impIntIp;
    }

    public Double getImpInternos() {
        return impInternos;
    }

    public void setImpInternos(Double impInternos) {
        this.impInternos = impInternos;
    }

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public List<ArticuloP> getArticuloPList() {
        return articuloPList;
    }

    public void setArticuloPList(List<ArticuloP> articuloPList) {
        this.articuloPList = articuloPList;
    }
}
