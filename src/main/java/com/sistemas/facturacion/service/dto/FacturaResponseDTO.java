package com.sistemas.facturacion.service.dto;

public class FacturaResponseDTO {

    private String numeroComprobante;
    private String CAE;
    private String fechaVencimiento;
    private boolean error;

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public String getCAE() {
        return CAE;
    }

    public void setCAE(String CAE) {
        this.CAE = CAE;
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
