package com.sistemas.facturacion.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "familiares")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Familiar {

    @EmbeddedId
    private FamiliarId id;

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

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "casamiento")
    private String casamiento;

    @Column(name = "nacimiento")
    private String nacimiento;

    @Column(name = "documento")
    private String documento;

    @Column(name = "bajaestima1")
    private String bajaEstima1;

    @Column(name = "cestudio1")
    private String cEstudio1;

    @Column(name = "bajaestima2")
    private String bajaEstima2;

    @Column(name = "cestudio2")
    private String cEstudio2;

    @Column(name = "bajaestima3")
    private String bajaEstima3;

    @Column(name = "cestudio3")
    private String cEstudio3;

    @Column(name = "bajaestima4")
    private String bajaEstima4;

    @Column(name = "cestudio4")
    private String cEstudio4;

    @Column(name = "bajaestima5")
    private String bajaEstima5;

    @Column(name = "cestudio5")
    private String cEstudio5;

    @Column(name = "fechaalta")
    private String fechaAlta;

    @Column(name = "bajaestimada")
    private String bajaEstimada;

    @Column(name = "fechabaja")
    private String fechaBaja;

    @Column(name = "motbaja")
    private String motivoBaja;

    @Column(name = "modificado")
    private String modificado;

    @Column(name = "bloqueado")
    private String bloqueado;

    @Column(name = "usualta")
    private String usuarioAlta;

    @Column(name = "fecalta")
    private String fechaUsuarioAlta;

    @Column(name = "usumodi")
    private String usuarioModificacion;

    @Column(name = "fecmodi")
    private String fechaUsuarioModificacion;

    @Column(name = "cbu")
    private String cbu;

    @Column(name = "nroioma")
    private String numeroIoma;

    @Column(name = "barra")
    private String barra;

    public FamiliarId getId() {
        return id;
    }

    public void setId(FamiliarId id) {
        this.id = id;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCasamiento() {
        return casamiento;
    }

    public void setCasamiento(String casamiento) {
        this.casamiento = casamiento;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getBajaEstima1() {
        return bajaEstima1;
    }

    public void setBajaEstima1(String bajaEstima1) {
        this.bajaEstima1 = bajaEstima1;
    }

    public String getcEstudio1() {
        return cEstudio1;
    }

    public void setcEstudio1(String cEstudio1) {
        this.cEstudio1 = cEstudio1;
    }

    public String getBajaEstima2() {
        return bajaEstima2;
    }

    public void setBajaEstima2(String bajaEstima2) {
        this.bajaEstima2 = bajaEstima2;
    }

    public String getcEstudio2() {
        return cEstudio2;
    }

    public void setcEstudio2(String cEstudio2) {
        this.cEstudio2 = cEstudio2;
    }

    public String getBajaEstima3() {
        return bajaEstima3;
    }

    public void setBajaEstima3(String bajaEstima3) {
        this.bajaEstima3 = bajaEstima3;
    }

    public String getcEstudio3() {
        return cEstudio3;
    }

    public void setcEstudio3(String cEstudio3) {
        this.cEstudio3 = cEstudio3;
    }

    public String getBajaEstima4() {
        return bajaEstima4;
    }

    public void setBajaEstima4(String bajaEstima4) {
        this.bajaEstima4 = bajaEstima4;
    }

    public String getcEstudio4() {
        return cEstudio4;
    }

    public void setcEstudio4(String cEstudio4) {
        this.cEstudio4 = cEstudio4;
    }

    public String getBajaEstima5() {
        return bajaEstima5;
    }

    public void setBajaEstima5(String bajaEstima5) {
        this.bajaEstima5 = bajaEstima5;
    }

    public String getcEstudio5() {
        return cEstudio5;
    }

    public void setcEstudio5(String cEstudio5) {
        this.cEstudio5 = cEstudio5;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getBajaEstimada() {
        return bajaEstimada;
    }

    public void setBajaEstimada(String bajaEstimada) {
        this.bajaEstimada = bajaEstimada;
    }

    public String getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(String fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMotivoBaja() {
        return motivoBaja;
    }

    public void setMotivoBaja(String motivoBaja) {
        this.motivoBaja = motivoBaja;
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

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getFechaUsuarioAlta() {
        return fechaUsuarioAlta;
    }

    public void setFechaUsuarioAlta(String fechaUsuarioAlta) {
        this.fechaUsuarioAlta = fechaUsuarioAlta;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public String getFechaUsuarioModificacion() {
        return fechaUsuarioModificacion;
    }

    public void setFechaUsuarioModificacion(String fechaUsuarioModificacion) {
        this.fechaUsuarioModificacion = fechaUsuarioModificacion;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getNumeroIoma() {
        return numeroIoma;
    }

    public void setNumeroIoma(String numeroIoma) {
        this.numeroIoma = numeroIoma;
    }

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

}
