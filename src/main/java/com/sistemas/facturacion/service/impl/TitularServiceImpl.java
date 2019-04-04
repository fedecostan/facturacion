package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Delegacion;
import com.sistemas.facturacion.model.Familiar;
import com.sistemas.facturacion.model.Titular;
import com.sistemas.facturacion.model.TitularA;
import com.sistemas.facturacion.repository.DelegacionRepository;
import com.sistemas.facturacion.repository.FamiliarRepository;
import com.sistemas.facturacion.repository.TitularARepository;
import com.sistemas.facturacion.repository.TitularRepository;
import com.sistemas.facturacion.service.TitularService;
import com.sistemas.facturacion.service.dto.TitularDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class TitularServiceImpl implements TitularService {

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private TitularARepository titularARepository;

    @Autowired
    private FamiliarRepository familiarRepository;

    @Autowired
    private DelegacionRepository delegacionRepository;

    @Override
    public List<TitularDTO> obtenerPorSindicato(String sindicato) {
        List<Titular> titularList = titularRepository.findByDelegacionAndFechaBaja(sindicato, "29991231");
        List<TitularDTO> titularDTOList = new ArrayList<>();
        for (Titular titular : titularList) {
            TitularDTO titularDTO = new TitularDTO();
            titularDTO.setValue(Integer.parseInt(titular.getNumeroRegistro()));
            titularDTO.setName(titular.getApellidoYNombre());
            titularDTO.setSindicato(sindicato);
            titularDTO.setIoma(titular.getIoma());
            titularDTO.setBarra(titular.getBarra());
            titularDTO.setFamilia(titular.getCodigoFamilia());
            titularDTO.setDni(titular.getNumeroDocumento());
            titularDTO.setBloqueado(titular.getBloqueado());
            titularDTO.setOrden(0);
            titularDTOList.add(titularDTO);
        }
        List<Familiar> familiarList = familiarRepository.findBySindicatoAndFechaBaja(sindicato, "29991231");
        for (Familiar familiar : familiarList) {
            TitularDTO titularDTO = new TitularDTO();
            titularDTO.setValue(Integer.parseInt(familiar.getId().getNroRegistro()));
            titularDTO.setName(familiar.getApellidoYNombre());
            titularDTO.setSindicato(sindicato);
            titularDTO.setIoma(familiar.getNumeroIoma());
            titularDTO.setBarra(familiar.getBarra());
            titularDTO.setFamilia(familiar.getCodigoFamilia());
            titularDTO.setDni(familiar.getNumeroDocumento());
            titularDTO.setBloqueado(familiar.getBloqueado());
            titularDTO.setOrden(familiar.getId().getOrden());
            titularDTOList.add(titularDTO);
        }
        Collections.sort(titularDTOList, new Comparator<TitularDTO>() {
            @Override
            public int compare(TitularDTO o1, TitularDTO o2) {
                int x1 = o1.getValue();
                int x2 = o2.getValue();
                int sComp = x1 - x2;
                if (sComp != 0) {
                    return sComp;
                }
                x1 = o1.getOrden();
                x2 = o2.getOrden();
                sComp = x1 - x2;
                return sComp;
            }
        });
        return titularDTOList;
    }

    @Override
    public TitularDTO obtenerPorIdYSindicato(Long id, Long sindicato) {
        String formattedTitular = String.format("%011d", id);
        String formattedSindicato = String.format("%03d", sindicato);
        Titular titular = titularRepository.findByDelegacionAndNumeroRegistroAndFechaBaja(formattedSindicato, formattedTitular, "29991231");
        TitularDTO titularDTO = new TitularDTO();
        if (titular != null) {
            titularDTO.setValue(Integer.parseInt(titular.getNumeroRegistro()));
            titularDTO.setName(titular.getApellidoYNombre());
            titularDTO.setSindicato(formattedSindicato);
            titularDTO.setIoma(titular.getIoma());
            titularDTO.setBarra(titular.getBarra());
            titularDTO.setFamilia(titular.getCodigoFamilia());
            titularDTO.setDni(titular.getNumeroDocumento());
            titularDTO.setBloqueado(titular.getBloqueado());
        } else {
            //TODO:PROBLEMA
            titularDTO.setName("N/A");
        }
        return titularDTO;
    }

}
