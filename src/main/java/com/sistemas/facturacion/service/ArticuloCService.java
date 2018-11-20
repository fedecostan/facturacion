package com.sistemas.facturacion.service;

import com.sistemas.facturacion.service.dto.ArticuloDTO;

import java.util.List;

public interface ArticuloCService {

    List<ArticuloDTO> obtenerTodos(String fecha);

    ArticuloDTO obtenerPorId(Long id, String fecha);
}
