package com.sistemas.facturacion.controller;

import com.sistemas.facturacion.service.*;
import com.sistemas.facturacion.service.dto.*;
import com.sistemas.facturacion.service.impl.PdfFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/cargarInformacion")
    public @ResponseBody
    InfoPantallaDTO cargarInformacion(){
        InfoPantallaDTO infoPantallaDTO = new InfoPantallaDTO();
        infoPantallaDTO.setTipoComprobanteDTOList(tipoComprobanteService.obtenerTodos());
        infoPantallaDTO.setSituacionesIVADTOList(situacionesIVAService.obtenerTodos());
        infoPantallaDTO.setCondicionVentaDTOList(condicionVentaService.obtenerTodos());
        return infoPantallaDTO;
    }

    @GetMapping(value = "/cargarLeyendaComprobante")
    public @ResponseBody
    TipoComprobanteDTO cargarLeyendaComprobante(@RequestParam("codigo") Integer codigo){
        return tipoComprobanteService.obtenerleyenda(codigo);
    }

    @GetMapping(value = "/cargarSindicatos")
    public @ResponseBody
    List<DelegacionDTO> cargarSindicatos(){
        return delegacionService.obtenerTodos();
    }

    @GetMapping(value = "/buscarSindicatoPorCodigo")
    public @ResponseBody
    DelegacionDTO buscarSindicatoPorCodigo(@RequestParam("codigo") String codigo){
        return delegacionService.buscarDelegacionPorCodigo(codigo);
    }

    @GetMapping(value = "/cargarAfiliados")
    public @ResponseBody
    List<TitularDTO> cargarAfiliados(){
        return titularService.obtenerTodos();
    }

    @GetMapping(value = "/cargarAfiliadosPorSindicato")
    public @ResponseBody
    List<TitularDTO> cargarAfiliadosPorSindicato(@RequestParam("sindicato") String sindicato){
        return titularService.obtenerPorSindicato(sindicato);
    }

    @GetMapping(value = "/buscarAfiliadoPorId")
    public @ResponseBody
    TitularDTO buscarAfiliadoPorId(@RequestParam("id") Long id){
        return titularService.obtenerPorId(id);
    }

    @GetMapping(value = "/buscarAfiliadoPorIdYSindicato")
    public @ResponseBody
    TitularDTO buscarAfiliadoPorIdYSindicato(@RequestParam("id") Long id, @RequestParam("sindicato") Long sindicato){
        return titularService.obtenerPorIdYSindicato(id,sindicato);
    }

    @GetMapping(value = "/cargarProductos")
    public @ResponseBody
    List<ArticuloDTO> cargarProductos(@RequestParam("fecha") String fecha){
        return articuloCService.obtenerTodos(fecha);
    }

    @GetMapping(value = "/cargarProductosPorId")
    public @ResponseBody
    ArticuloDTO cargarProductosPorId(@RequestParam("id") Long id, @RequestParam("fecha") String fecha){
        return articuloCService.obtenerPorId(id, fecha);
    }

    @PostMapping(value = "/generarFactura")
    public FacturaResponseDTO generarFactura(@RequestBody FacturaDTO facturaDTO){
        return facturaService.generarFactura(facturaDTO);
    }

    @PostMapping(value = "/imprimirFactura")
    public void imprimirFactura(@RequestBody DatosFacturaDTO datosFacturaDTO, @RequestParam("mail") String mail){
        PdfFactura pdfFactura = new PdfFactura();
        pdfFactura.imprimirFactura(datosFacturaDTO,mail);
    }

}
