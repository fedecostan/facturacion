package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Autorizacion;
import com.sistemas.facturacion.model.Delegacion;
import com.sistemas.facturacion.model.TipoDocumento;
import com.sistemas.facturacion.model.Titular;
import com.sistemas.facturacion.repository.DelegacionRepository;
import com.sistemas.facturacion.repository.EmpresaRepository;
import com.sistemas.facturacion.repository.TipoDocumentoRepository;
import com.sistemas.facturacion.repository.TitularRepository;
import com.sistemas.facturacion.service.FacturaService;
import com.sistemas.facturacion.service.afipFac.*;
import com.sistemas.facturacion.service.dto.FacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl extends AfipWS implements FacturaService {

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private DelegacionRepository delegacionRepository;

    @Value("${cuit}")
    private Long cuit;

    @Override
    public String generarFactura(FacturaDTO facturaDTO) {
        String factura = "";
        try {
            Autorizacion autorizacion = obtenerAutorizacion();
            factura = solicitarCae(autorizacion, facturaDTO);
        } catch (Exception e){
            e.printStackTrace();
        }
        return factura;
    }

    private Long obtenerUltimoComprobante(Autorizacion autorizacion, FacturaDTO facturaDTO) {
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FEAuthRequest autenticacion = new FEAuthRequest();
        autenticacion.setToken(autorizacion.getToken());
        autenticacion.setSign(autorizacion.getSign());
        autenticacion.setCuit(cuit);
        FERecuperaLastCbteResponse feRecuperaLastCbteResponse = serviceSoap.feCompUltimoAutorizado(autenticacion,Integer.parseInt(facturaDTO.getPuntoVenta()),Integer.parseInt(facturaDTO.getTipoComprobante()));
        return new Long(feRecuperaLastCbteResponse.getCbteNro());
    }

    private String solicitarCae(Autorizacion autorizacion, FacturaDTO facturaDTO) throws Exception{
        if (autorizacion==null) throw new Exception();
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FEAuthRequest autenticacion = new FEAuthRequest();
        autenticacion.setToken(autorizacion.getToken());
        autenticacion.setSign(autorizacion.getSign());
        autenticacion.setCuit(cuit);
        FECAERequest request = new FECAERequest();
        FECAECabRequest cabecera = new FECAECabRequest();
        cabecera.setCantReg(1);
        cabecera.setPtoVta(Integer.parseInt(facturaDTO.getPuntoVenta())); //Obtener de factura
        cabecera.setCbteTipo(Integer.parseInt(facturaDTO.getTipoComprobante())); //Obtener de factura
        request.setFeCabReq(cabecera);
        ArrayOfFECAEDetRequest arrayOfFECAEDetRequest = new ArrayOfFECAEDetRequest();
        FECAEDetRequest detalle = createDetalle(autorizacion, facturaDTO);
        arrayOfFECAEDetRequest.getFECAEDetRequest().add(detalle);
        request.setFeDetReq(arrayOfFECAEDetRequest);
        FECAEResponse s = serviceSoap.fecaeSolicitar(autenticacion,request);
        System.out.println(s);
        boolean error = false;
        try {
            if (s.getErrors().getErr().get(0).getCode() == 600) error = true;
        } catch (Exception e){}
        if (error) throw new Exception();
        return s.toString();
    }

    private FECAEDetRequest createDetalle(Autorizacion autorizacion, FacturaDTO facturaDTO) {
        FECAEDetRequest detalle = new FECAEDetRequest();
        detalle.setConcepto(empresaRepository.findAll().get(0).getConcepto()); //1- Producto 2- Servicio 3- Producto y Servicio
        if (facturaDTO.getAfiliado()!=null) {
            Titular titular = titularRepository.findByNumeroRegistro(facturaDTO.getAfiliado());
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findByCodigo(titular.getTipoDocumento());
            detalle.setDocTipo(tipoDocumento.getCodigoAfip());
            detalle.setDocNro(Long.valueOf(titular.getNumeroDocumento()));
        } else {
            Delegacion delegacion = delegacionRepository.findByCodigo(facturaDTO.getSindicato());
            TipoDocumento tipoDocumento = tipoDocumentoRepository.findByCodigo(delegacion.getTipoDocumento());
            detalle.setDocTipo(tipoDocumento.getCodigoAfip());
            detalle.setDocNro(Long.valueOf(delegacion.getCuit()));
        }
        Long numeroComprobante = obtenerUltimoComprobante(autorizacion, facturaDTO)+1;
        detalle.setCbteDesde(numeroComprobante);
        detalle.setCbteHasta(numeroComprobante);
        detalle.setCbteFch(facturaDTO.getFecha());
        detalle.setImpTotal(Double.parseDouble(facturaDTO.getTotal()));
        detalle.setImpNeto(Double.parseDouble(facturaDTO.getTotal()));
        detalle.setImpTotConc(0);
        detalle.setImpIVA(0);
        detalle.setImpOpEx(0);
        detalle.setImpTrib(0);
        detalle.setMonId("PES");
        detalle.setMonCotiz(1);
        return detalle;
    }



}
