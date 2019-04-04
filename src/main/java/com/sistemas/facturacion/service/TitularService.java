package com.sistemas.facturacion.service;

import com.sistemas.facturacion.service.dto.TitularDTO;

import java.util.List;

public interface TitularService {

    List<TitularDTO> obtenerPorSindicato(String sindicato);

    TitularDTO obtenerPorIdYSindicato(Long id, Long sindicato);
}
