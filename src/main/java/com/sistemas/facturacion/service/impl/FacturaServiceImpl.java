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
import com.sistemas.facturacion.service.dto.FacturaResponseDTO;
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
    public FacturaResponseDTO generarFactura(FacturaDTO facturaDTO) {
        FacturaResponseDTO facturaResponseDTO = new FacturaResponseDTO();
        try {
            Autorizacion autorizacion = obtenerAutorizacion();
            facturaResponseDTO = solicitarCae(autorizacion, facturaDTO);
        } catch (Exception e){
            e.printStackTrace();
        }
        return facturaResponseDTO;
    }

    public FacturaResponseDTO solicitarCae(Autorizacion autorizacion, FacturaDTO facturaDTO) throws Exception{
        if (autorizacion==null) throw new Exception();
        FECAERequest request = new FECAERequest();
        request.setFeCabReq(crearCabecera(facturaDTO));
        FECAEDetRequest detalle = createDetalle(autorizacion, facturaDTO);
        ArrayOfFECAEDetRequest arrayOfFECAEDetRequest = new ArrayOfFECAEDetRequest();
        arrayOfFECAEDetRequest.getFECAEDetRequest().add(detalle);
        request.setFeDetReq(arrayOfFECAEDetRequest);
        FacturaResponseDTO cae = generarFactura(crearAutorizacion(autorizacion),request);
        grabarfactura(cae,facturaDTO);
        return cae;
    }

    private FEAuthRequest crearAutorizacion(Autorizacion autorizacion) {
        FEAuthRequest autenticacion = new FEAuthRequest();
        autenticacion.setToken(autorizacion.getToken());
        autenticacion.setSign(autorizacion.getSign());
        autenticacion.setCuit(cuit);
        return autenticacion;
    }

    private FECAECabRequest crearCabecera(FacturaDTO facturaDTO) {
        FECAECabRequest cabecera = new FECAECabRequest();
        cabecera.setCantReg(1);
        cabecera.setPtoVta(Integer.parseInt(facturaDTO.getPuntoVenta()));
        cabecera.setCbteTipo(Integer.parseInt(facturaDTO.getTipoComprobante()));
        return cabecera;
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
        detalle.setCbteFch(formatearFecha(facturaDTO.getFecha()));
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

    private static String formatearFecha (String fecha){
        String dia = fecha.substring(0,2);
        String mes = fecha.substring(3,5);
        String anio = fecha.substring(6);
        return anio+mes+dia;
    }

    private void grabarfactura(FacturaResponseDTO cae, FacturaDTO facturaDTO) {
        if (!cae.isError()){

        }
    }

}
