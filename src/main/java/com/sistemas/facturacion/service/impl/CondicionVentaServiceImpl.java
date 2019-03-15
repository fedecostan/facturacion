package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.CondicionVenta;
import com.sistemas.facturacion.repository.CondicionVentaRepository;
import com.sistemas.facturacion.service.CondicionVentaService;
import com.sistemas.facturacion.service.dto.CondicionVentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondicionVentaServiceImpl implements CondicionVentaService {

    @Autowired
    private CondicionVentaRepository condicionVentaRepository;

    @Override
    public List<CondicionVentaDTO> obtenerTodos() {
        List<CondicionVenta> condicionVentaList = condicionVentaRepository.findTodos();
        List<CondicionVentaDTO> condicionVentaDTOList = new ArrayList<>();
        for (CondicionVenta condicionVenta : condicionVentaList){
            CondicionVentaDTO condicionVentaDTO = new CondicionVentaDTO();
            condicionVentaDTO.setName(condicionVenta.getDescripcion());
            condicionVentaDTO.setValue(condicionVenta.getCodigo());
            condicionVentaDTOList.add(condicionVentaDTO);
        }
        return condicionVentaDTOList;
    }
}
