package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Autorizacion;
import com.sistemas.facturacion.model.Delegacion;
import com.sistemas.facturacion.model.Titular;
import com.sistemas.facturacion.repository.DelegacionRepository;
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
    private DelegacionRepository delegacionRepository;

    @Value("${cuit}")
    private Long cuit;

    @Value("${cae.concept}")
    private int concept;

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
        detalle.setConcepto(concept); //1- Producto 2- Servicio 3- Producto y Servicio
        if (facturaDTO.getAfiliado()!=null) {
            Titular titular = titularRepository.findByNumeroRegistro(facturaDTO.getAfiliado());
            detalle.setDocTipo(Integer.parseInt(titular.getTipoDocumento()));
            detalle.setDocNro(Long.valueOf(titular.getNumeroDocumento()));
        } else {
            Delegacion delegacion = delegacionRepository.findByCodigo(facturaDTO.getSindicato());
//            detalle.setDocTipo(Integer.parseInt(delegacion.));
//            detalle.setDocNro(Long.valueOf(delegacion.getNumeroDocumento()));
        }
        Long numeroComprobante = obtenerUltimoComprobante(autorizacion, facturaDTO)+1;
        detalle.setCbteDesde(numeroComprobante);
        detalle.setCbteHasta(numeroComprobante);
        detalle.setCbteFch(facturaDTO.getFecha());
        detalle.setImpTotal(Double.parseDouble(facturaDTO.getTotal()));
        detalle.setImpNeto(0);
        detalle.setImpTotConc(0);
        detalle.setImpIVA(0);
        detalle.setImpOpEx(0);
        if (Integer.parseInt(facturaDTO.getTipoComprobante()) == 12){
            Double total = Double.parseDouble(facturaDTO.getTotal());
            Double iva = Double.parseDouble(facturaDTO.getSituacionesIva());
            Double impNeto = total/((iva+100)/100);
            detalle.setImpNeto(impNeto);
            detalle.setImpIVA(total-impNeto);
            ArrayOfAlicIva arrayOfAlicIva = new ArrayOfAlicIva();
            AlicIva alicIva = new AlicIva();
            alicIva.setBaseImp(impNeto);
            alicIva.setImporte(total-impNeto);
            alicIva.setId(5);
            arrayOfAlicIva.getAlicIva().add(alicIva);
            detalle.setIva(arrayOfAlicIva);
        }
        detalle.setImpTrib(0);
        if (concept==1){
            detalle.setFchServDesde(facturaDTO.getFecha());
            detalle.setFchServHasta(facturaDTO.getFecha());
            detalle.setFchVtoPago(facturaDTO.getFecha());
        }
        detalle.setMonId("PES");
        detalle.setMonCotiz(1);
        return detalle;
    }



}
