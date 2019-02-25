package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.MovimientoCliente;
import com.sistemas.facturacion.model.TipoComprobante;
import com.sistemas.facturacion.repository.MovimientoClienteRepository;
import com.sistemas.facturacion.repository.TipoComprobanteRepository;
import com.sistemas.facturacion.service.ComprobanteService;
import com.sistemas.facturacion.service.dto.ComprobanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    @Autowired
    private MovimientoClienteRepository movimientoClienteRepository;

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

//    @Override
//    public ComprobanteDTO buscarUltimoComprobante(String tipoComprobante) {
//        TipoComprobante tc = tipoComprobanteRepository.findByCodigoAfip(tipoComprobante);
//        MovimientoCliente movimientoCliente = movimientoClienteRepository.findFirstByTipoComprobanteOrderByNumeroComprobanteDesc(tc.getTipo());
//        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
//        if (movimientoCliente != null){
//            comprobanteDTO.setNumero(movimientoCliente.getNumeroComprobante());
//            comprobanteDTO.setFecha(movimientoCliente.getFecha());
//        } else {
//            comprobanteDTO.setNumero("1");
//        }
//        return comprobanteDTO;
//    }
}
