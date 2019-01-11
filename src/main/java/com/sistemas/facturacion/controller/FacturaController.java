package com.sistemas.facturacion.controller;

import com.sistemas.facturacion.service.*;
import com.sistemas.facturacion.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("/facturaController")
public class FacturaController {

    @Autowired
    private TipoComprobanteService tipoComprobanteService;

    @Autowired
    private SituacionesIVAService situacionesIVAService;

    @Autowired
    private CondicionVentaService condicionVentaService;

    @Autowired
    private DelegacionService delegacionService;

    @Autowired
    private TitularService titularService;

    @Autowired
    private ArticuloCService articuloCService;

    @Autowired
    private ComprobanteService comprobanteService;

    @Autowired
    private FacturaService facturaService;

    @RequestMapping(value = "/cargarInformacion", method = RequestMethod.GET)
    public @ResponseBody
    InfoPantallaDTO cargarInformacion(){
        InfoPantallaDTO infoPantallaDTO = new InfoPantallaDTO();
        infoPantallaDTO.setTipoComprobanteDTOList(tipoComprobanteService.obtenerTodos());
        infoPantallaDTO.setSituacionesIVADTOList(situacionesIVAService.obtenerTodos());
        infoPantallaDTO.setCondicionVentaDTOList(condicionVentaService.obtenerTodos());
        return infoPantallaDTO;
    }

    @RequestMapping(value = "/cargarLeyendaComprobante", method = RequestMethod.GET)
    public @ResponseBody
    TipoComprobanteDTO cargarLeyendaComprobante(@RequestParam("codigo") String codigo){
        return tipoComprobanteService.obtenerleyenda(codigo);
    }

    @RequestMapping(value = "/cargarSindicatos", method = RequestMethod.GET)
    public @ResponseBody
    List<DelegacionDTO> cargarSindicatos(){
        return delegacionService.obtenerTodos();
    }

    @RequestMapping(value = "/buscarSindicatoPorCodigo", method = RequestMethod.GET)
    public @ResponseBody
    DelegacionDTO buscarSindicatoPorCodigo(@RequestParam("codigo") String codigo){
        return delegacionService.buscarDelegacionPorCodigo(codigo);
    }

    @RequestMapping(value = "/cargarAfiliados", method = RequestMethod.GET)
    public @ResponseBody
    List<TitularDTO> cargarAfiliados(){
        return titularService.obtenerTodos();
    }

    @RequestMapping(value = "/cargarAfiliadosPorSindicato", method = RequestMethod.GET)
    public @ResponseBody
    List<TitularDTO> cargarAfiliadosPorSindicato(@RequestParam("sindicato") String sindicato){
        return titularService.obtenerPorSindicato(sindicato);
    }

    @RequestMapping(value = "/buscarAfiliadoPorId", method = RequestMethod.GET)
    public @ResponseBody
    TitularDTO buscarAfiliadoPorId(@RequestParam("id") Long id){
        return titularService.obtenerPorId(id);
    }

    @RequestMapping(value = "/buscarAfiliadoPorIdYSindicato", method = RequestMethod.GET)
    public @ResponseBody
    TitularDTO buscarAfiliadoPorIdYSindicato(@RequestParam("id") Long id, @RequestParam("sindicato") Long sindicato){
        return titularService.obtenerPorIdYSindicato(id,sindicato);
    }

    @RequestMapping(value = "/cargarProductos", method = RequestMethod.GET)
    public @ResponseBody
    List<ArticuloDTO> cargarProductos(@RequestParam("fecha") String fecha){
        return articuloCService.obtenerTodos(fecha);
    }

    @RequestMapping(value = "/cargarProductosPorId", method = RequestMethod.GET)
    public @ResponseBody
    ArticuloDTO cargarProductosPorId(@RequestParam("id") Long id, @RequestParam("fecha") String fecha){
        return articuloCService.obtenerPorId(id, fecha);
    }

    @RequestMapping(value = "/buscarComprobante", method = RequestMethod.GET)
    public @ResponseBody
    ComprobanteDTO buscarComprobante(@RequestParam("comp") String tipoComprobante){
        return comprobanteService.buscarUltimoComprobante(tipoComprobante);
    }

    @RequestMapping(value = "/generarFactura", method = RequestMethod.GET)
    public void generarFactura(){
        try {
            facturaService.generarFactura();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
