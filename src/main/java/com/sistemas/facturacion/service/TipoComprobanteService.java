package com.sistemas.facturacion.service;

import com.sistemas.facturacion.service.dto.TipoComprobanteDTO;

import java.util.List;

public interface TipoComprobanteService {

    List<TipoComprobanteDTO> obtenerTodos();

    TipoComprobanteDTO obtenerleyenda(Integer codigo);
}
