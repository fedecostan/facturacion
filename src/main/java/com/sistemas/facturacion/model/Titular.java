package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "titulares")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Titular {

    @Id
    @Column(name = "nroregistro")
    private String numeroRegistro;

    @Column(name = "codflia")
    private String codigoFamilia;

    @Column(name = "cuil")
    private String cuil;

    @Column(name = "cuilhasta")
    private String cuilHasta;

    @Column(name = "cuiln")
    private String cuilN;

    @Column(name = "apyno")
    private String apellidoYNombre;

    @Column(name = "fechanac")
    private String fechaNacimiento;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "estadocivil")
    private String estadoCivil;

    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Column(name = "tipodoc")
    private String tipoDocumento;

    @Column(name = "nrodoc")
    private String numeroDocumento;

    @Column(name = "nrodochasta")
    private String numeroDocumentoHasta;

    @Column(name = "tipodocn")
    private String tipoDocumentoN;

    @Column(name = "nrodocn")
    private String numeroDocumentoN;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email")
    private String email;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private String numero;

    @Column(name = "piso")
    private String piso;

    @Column(name = "depto")
    private String departamento;

    @Column(name = "localidad")
    private String localidad;

    @Column(name = "zona")
    private String zona;

    @Column(name = "provincia")
    private String provincia;

    @Column(name = "pais")
    private String pais;

    @Column(name = "codigopostal")
    private String codigoPostal;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "modificado")
    private String modificado;

    @Column(name = "bloqueado")
    private String bloqueado;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "ioma")
    private String ioma;

    @Column(name = "usualta")
    private String usuarioAlta;

    @Column(name = "fecalta")
    private String fechaAlta;

    @Column(name = "usumodi")
    private String usuarioModificacion;

    @Column(name = "fecmodi")
    private String fechaModificacion;

    @Column(name = "barra")
    private String barra;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "concbu")
    private int conCbu;

    @Column(name = "modoenvio")
    private String modoEnvio;

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getCodigoFamilia() {
        return codigoFamilia;
    }

    public void setCodigoFamilia(String codigoFamilia) {
        this.codigoFamilia = codigoFamilia;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getCuilHasta() {
        return cuilHasta;
    }

    public void setCuilHasta(String cuilHasta) {
        this.cuilHasta = cuilHasta;
    }

    public String getCuilN() {
        return cuilN;
    }

    public void setCuilN(String cuilN) {
        this.cuilN = cuilN;
    }

    public String getApellidoYNombre() {
        return apellidoYNombre;
    }

    public void setApellidoYNombre(String apellidoYNombre) {
        this.apellidoYNombre = apellidoYNombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroDocumentoHasta() {
        return numeroDocumentoHasta;
    }

    public void setNumeroDocumentoHasta(String numeroDocumentoHasta) {
        this.numeroDocumentoHasta = numeroDocumentoHasta;
    }

    public String getTipoDocumentoN() {
        return tipoDocumentoN;
    }

    public void setTipoDocumentoN(String tipoDocumentoN) {
        this.tipoDocumentoN = tipoDocumentoN;
    }

    public String getNumeroDocumentoN() {
        return numeroDocumentoN;
    }

    public void setNumeroDocumentoN(String numeroDocumentoN) {
        this.numeroDocumentoN = numeroDocumentoN;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getIoma() {
        return ioma;
    }

    public void setIoma(String ioma) {
        this.ioma = ioma;
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

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getConCbu() {
        return conCbu;
    }

    public void setConCbu(int conCbu) {
        this.conCbu = conCbu;
    }

    public String getModoEnvio() {
        return modoEnvio;
    }

    public void setModoEnvio(String modoEnvio) {
        this.modoEnvio = modoEnvio;
    }

}
