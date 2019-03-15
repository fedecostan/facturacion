package com.sistemas.facturacion.service.dto;

public class FacturaResponseDTO {

    private String numeroComprobante;
    private String cae;
    private String fechaVencimiento;
    private boolean error;

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getCAE() {
        return cae;
    }

    public void setCAE(String cae) {
        this.cae = cae;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
