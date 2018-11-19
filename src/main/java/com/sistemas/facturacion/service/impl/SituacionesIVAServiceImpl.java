package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.SituacionesIVA;
import com.sistemas.facturacion.model.TipoComprobante;
import com.sistemas.facturacion.repository.SituacionesIVARepository;
import com.sistemas.facturacion.repository.TipoComprobanteRepository;
import com.sistemas.facturacion.service.SituacionesIVAService;
import com.sistemas.facturacion.service.TipoComprobanteService;
import com.sistemas.facturacion.service.dto.SituacionesIVADTO;
import com.sistemas.facturacion.service.dto.TipoComprobanteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SituacionesIVAServiceImpl implements SituacionesIVAService {

    @Autowired
    private SituacionesIVARepository situacionesIVARepository;

    @Override
    public List<SituacionesIVADTO> obtenerTodos() {
        List<SituacionesIVA> situacionesIVAList = situacionesIVARepository.findAll();
        List<SituacionesIVADTO> situacionesIVADTOList = new ArrayList<>();
        for (SituacionesIVA situacionesIVA : situacionesIVAList){
            SituacionesIVADTO situacionesIVADTO = new SituacionesIVADTO();
            situacionesIVADTO.setName(situacionesIVA.getDescripcion());
            situacionesIVADTO.setValue(situacionesIVA.getCodigo());
            situacionesIVADTOList.add(situacionesIVADTO);
        }
        return situacionesIVADTOList;
    }
}
