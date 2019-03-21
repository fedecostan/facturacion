package com.sistemas.facturacion.service.dto;

public class DatosFacturaDTO {

    private FacturaDTO facturaDTO;
    private FacturaResponseDTO facturaResponseDTO;

    public FacturaDTO getFacturaDTO() {
        return facturaDTO;
    }

    public void setFacturaDTO(FacturaDTO facturaDTO) {
        this.facturaDTO = facturaDTO;
    }

    public FacturaResponseDTO getFacturaResponseDTO() {
        return facturaResponseDTO;
    }

    public void setFacturaResponseDTO(FacturaResponseDTO facturaResponseDTO) {
        this.facturaResponseDTO = facturaResponseDTO;
    }
}
