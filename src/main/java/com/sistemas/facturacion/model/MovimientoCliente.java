package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "moviclien")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MovimientoCliente {

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

    @Column(name = "cliente")
    private String cliente;

    @Column(name = "sitiva")
    private String situacionIva;

    @Column(name = "forpago")
    private String formaPago;

    @Column(name = "expreso")
    private String expreso;

    @Column(name = "listaprecio")
    private String listaPrecio;

    @Column(name = "bonificacion")
    private Double bonificacion;

    @Column(name = "iva")
    private Double iva;

    @Column(name = "ivanoinsc")
    private Double ivaNoInsc;

    @Column(name = "totalcomprobante")
    private Double totalComprobante;

    @Column(name = "imppagado")
    private Double impuestoPagado;

    @Column(name = "anulado")
    private String anulado;

    @Column(name = "leyenda")
    private String leyenda;

    @Column(name = "usuarioalta")
    private String usuarioAlta;

    @Column(name = "fechaalta")
    private String fechaAlta;

    @Column(name = "usuariomodi")
    private String usuarioModificacion;

    @Column(name = "fechamodi")
    private String fechaModificacion;

    @Column(name = "codicomprobanter")
    private String codigoComprobanteR;

    @Column(name = "nrocomprobanter")
    private String numeroComprobanteR;

    @Column(name = "tipocomprobanter")
    private String tipoComprobanteR;

    @Column(name = "motivocd")
    private String motivoCD;

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

    @Column(name = "ordenservicio")
    private int ordenServicio;

    @Column(name = "delegacion")
    private String delegacion;

    @Column(name = "codrechazo")
    private String codigoRechazo;

    @Column(name = "codflia")
    private String codigoFamilia;

    @Column(name = "ordenflia")
    private int ordenFamilia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getSituacionIva() {
        return situacionIva;
    }

    public void setSituacionIva(String situacionIva) {
        this.situacionIva = situacionIva;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getExpreso() {
        return expreso;
    }

    public void setExpreso(String expreso) {
        this.expreso = expreso;
    }

    public String getListaPrecio() {
        return listaPrecio;
    }

    public void setListaPrecio(String listaPrecio) {
        this.listaPrecio = listaPrecio;
    }

    public Double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getIvaNoInsc() {
        return ivaNoInsc;
    }

    public void setIvaNoInsc(Double ivaNoInsc) {
        this.ivaNoInsc = ivaNoInsc;
    }

    public Double getTotalComprobante() {
        return totalComprobante;
    }

    public void setTotalComprobante(Double totalComprobante) {
        this.totalComprobante = totalComprobante;
    }

    public Double getImpuestoPagado() {
        return impuestoPagado;
    }

    public void setImpuestoPagado(Double impuestoPagado) {
        this.impuestoPagado = impuestoPagado;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getLeyenda() {
        return leyenda;
    }

    public void setLeyenda(String leyenda) {
        this.leyenda = leyenda;
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

    public String getMotivoCD() {
        return motivoCD;
    }

    public void setMotivoCD(String motivoCD) {
        this.motivoCD = motivoCD;
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

    public String getCodigoRechazo() {
        return codigoRechazo;
    }

    public void setCodigoRechazo(String codigoRechazo) {
        this.codigoRechazo = codigoRechazo;
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
