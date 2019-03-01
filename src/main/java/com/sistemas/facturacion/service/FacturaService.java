package com.sistemas.facturacion.service;

import com.sistemas.facturacion.service.dto.FacturaDTO;
import com.sistemas.facturacion.service.dto.FacturaResponseDTO;

public interface FacturaService {

    FacturaResponseDTO generarFactura(FacturaDTO facturaDTO);

}
