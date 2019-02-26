package com.sistemas.facturacion.service.dto;

public class FacturaResponseDTO {

    private String numeroComprobante;
    private String CAE;

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
}
