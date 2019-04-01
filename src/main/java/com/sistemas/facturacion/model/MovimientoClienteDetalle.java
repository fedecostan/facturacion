package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "movicliend")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoClienteDetalle {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "codicomprobante")
    private String codigoComprobante;

    @Column(name = "nrocomprobante")
    private String numeroComprobante;

    @Column(name = "tipocomprobante")
    private String tipoComprobante;

    @Column(name = "orden")
    private Short orden;

    @Column(name = "indicativo")
    private String indicativo;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "cantidad")
    private Double cantidad;

    @Column(name = "unidad")
    private String unidad;

    @Column(name = "unixbulto")
    private int unixBulto;

    @Column(name = "preciounitario")
    private Double precioUnitario;

    @Column(name = "importe")
    private Double importe;

    @Column(name = "impbonificacion")
    private Double impBonificacion;

    @Column(name = "impiva")
    private Double impIva;

    @Column(name = "impnoinsc")
    private Double impNoInsc;

    @Column(name = "impinternos")
    private Double impInternos;

    @Column(name = "impintporcentaje")
    private Double impIntPorcentaje;

    @Column(name = "total")
    private Double total;

    @Column(name = "basearticulo")
    private Double baseArticulo;

    @Column(name = "aperturaarticulo")
    private Double aperturaArticulo;

    @Column(name = "anulado")
    private String anulado;

    @Column(name = "codicomprobanter")
    private String codigoComprobanteR;

    @Column(name = "nrocomprobanter")
    private String numeroComprobanteR;

    @Column(name = "tipocomprobanter")
    private String tipoComprobanteR;

    @Column(name = "leyenda")
    private String leyenda;

    @Column(name = "quienes")
    private String quienes;

    @Column(name = "organismo")
    private String organismo;

    @Column(name = "periodo")
    private String periodo;

    @Column(name = "nrocuota")
    private int numeroCuota;

    @Column(name = "cuotas")
    private int cuotas;

    @Column(name = "nroliq")
    private String numeroLiq;

    @Column(name = "nroenv")
    private String numeroEnv;

    @Column(name = "adescuento")
    private String aDescuento;

    @Column(name = "afiliado")
    private String afiliado;

    @Column(name = "ordenservicio")
    private int ordenServicio;

    @Column(name = "delegacion")
    private String delegacion;

    @Column(name = "bonodesdehasta")
    private String bonoDesdeHasta;

    @Column(name = "costo")
    private Double costo;

    @Column(name = "codflia")
    private String codigoFamilia;

    @Column(name = "ordenflia")
    private int ordenFamilia;

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

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public Short getOrden() {
        return orden;
    }

    public void setOrden(Short orden) {
        this.orden = orden;
    }

    public String getIndicativo() {
        return indicativo;
    }

    public void setIndicativo(String indicativo) {
        this.indicativo = indicativo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public int getUnixBulto() {
        return unixBulto;
    }

    public void setUnixBulto(int unixBulto) {
        this.unixBulto = unixBulto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getImpBonificacion() {
        return impBonificacion;
    }

    public void setImpBonificacion(Double impBonificacion) {
        this.impBonificacion = impBonificacion;
    }

    public Double getImpIva() {
        return impIva;
    }

    public void setImpIva(Double impIva) {
        this.impIva = impIva;
    }

    public Double getImpNoInsc() {
        return impNoInsc;
    }

    public void setImpNoInsc(Double impNoInsc) {
        this.impNoInsc = impNoInsc;
    }

    public Double getImpInternos() {
        return impInternos;
    }

    public void setImpInternos(Double impInternos) {
        this.impInternos = impInternos;
    }

    public Double getImpIntPorcentaje() {
        return impIntPorcentaje;
    }

    public void setImpIntPorcentaje(Double impIntPorcentaje) {
        this.impIntPorcentaje = impIntPorcentaje;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getBaseArticulo() {
        return baseArticulo;
    }

    public void setBaseArticulo(Double baseArticulo) {
        this.baseArticulo = baseArticulo;
    }

    public Double getAperturaArticulo() {
        return aperturaArticulo;
    }

    public void setAperturaArticulo(Double aperturaArticulo) {
        this.aperturaArticulo = aperturaArticulo;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getCodigoComprobanteR() {
        return codigoComprobanteR;
    }

    public void setCodigoComprobanteR(String codigoComprobanteR) {
        this.codigoComprobanteR = codigoComprobanteR;
    }

    public String getNumeroComprobanteR() {
        return numeroComprobanteR;
    }

    public void setNumeroComprobanteR(String numeroComprobanteR) {
        this.numeroComprobanteR = numeroComprobanteR;
    }

    public String getTipoComprobanteR() {
        return tipoComprobanteR;
    }

    public void setTipoComprobanteR(String tipoComprobanteR) {
        this.tipoComprobanteR = tipoComprobanteR;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
    }

    public String getQuienes() {
        return quienes;
    }

    public void setQuienes(String quienes) {
        this.quienes = quienes;
    }

    public String getOrganismo() {
        return organismo;
    }

    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public String getNumeroLiq() {
        return numeroLiq;
    }

    public void setNumeroLiq(String numeroLiq) {
        this.numeroLiq = numeroLiq;
    }

    public String getNumeroEnv() {
        return numeroEnv;
    }

    public void setNumeroEnv(String numeroEnv) {
        this.numeroEnv = numeroEnv;
    }

    public String getaDescuento() {
        return aDescuento;
    }

    public void setaDescuento(String aDescuento) {
        this.aDescuento = aDescuento;
    }

    public String getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(String afiliado) {
        this.afiliado = afiliado;
    }

    public int getOrdenServicio() {
        return ordenServicio;
    }

    public void setOrdenServicio(int ordenServicio) {
        this.ordenServicio = ordenServicio;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }

    public String getBonoDesdeHasta() {
        return bonoDesdeHasta;
    }

    public void setBonoDesdeHasta(String bonoDesdeHasta) {
        this.bonoDesdeHasta = bonoDesdeHasta;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getCodigoFamilia() {
        return codigoFamilia;
    }

    public void setCodigoFamilia(String codigoFamilia) {
        this.codigoFamilia = codigoFamilia;
    }

    public int getOrdenFamilia() {
        return ordenFamilia;
    }

    public void setOrdenFamilia(int ordenFamilia) {
        this.ordenFamilia = ordenFamilia;
    }
}
