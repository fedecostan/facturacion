package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.TipoComprobante;
import com.sistemas.facturacion.repository.TipoComprobanteRepository;
import com.sistemas.facturacion.service.TipoComprobanteService;
import com.sistemas.facturacion.service.dto.TipoComprobanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TipoComprobanteServiceImpl implements TipoComprobanteService {

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Override
    public List<TipoComprobanteDTO> obtenerTodos() {
        List<TipoComprobante> tipoComprobanteList = tipoComprobanteRepository.findTodos();
        List<TipoComprobanteDTO> tipoComprobanteDTOList = new ArrayList<>();
        for (TipoComprobante tipoComprobante : tipoComprobanteList){
            if (tipoComprobante.getCodigoAfip()!=null && tipoComprobante.getCodigoAfip() > 0) {
                TipoComprobanteDTO tipoComprobanteDTO = new TipoComprobanteDTO();
                tipoComprobanteDTO.setName(tipoComprobante.getDescripcion());
                tipoComprobanteDTO.setValue(tipoComprobante.getCodigoAfip());
                tipoComprobanteDTOList.add(tipoComprobanteDTO);
            }
        }
        return tipoComprobanteDTOList;
    }

    @Override
    public TipoComprobanteDTO obtenerleyenda(Integer codigoAfip) {
        TipoComprobante tipoComprobante = tipoComprobanteRepository.findByCodigoAfip(codigoAfip);
        TipoComprobanteDTO tipoComprobanteDTO = new TipoComprobanteDTO();
        tipoComprobanteDTO.setName(tipoComprobante.getLeyenda());
        return tipoComprobanteDTO;
    }
}
