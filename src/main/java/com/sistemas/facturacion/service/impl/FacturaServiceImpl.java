package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.*;
import com.sistemas.facturacion.repository.*;
import com.sistemas.facturacion.service.ArticuloCService;
import com.sistemas.facturacion.service.FacturaService;
import com.sistemas.facturacion.service.afipfac.*;
import com.sistemas.facturacion.service.dto.FacturaDTO;
import com.sistemas.facturacion.service.dto.FacturaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    @Autowired
    private ArticuloCService articuloCService;

    @Autowired
    private MovimientoClienteRepository movimientoClienteRepository;

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    private SituacionesIVARepository situacionesIVARepository;

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
        cabecera.setCbteTipo(facturaDTO.getTipoComprobante());
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
        detalle.setImpTotal(facturaDTO.getTotal());
        detalle.setImpNeto(facturaDTO.getTotal());
        detalle.setImpTotConc(0);
        detalle.setImpIVA(0);
        detalle.setImpOpEx(0);
        detalle.setImpTrib(0);
        detalle.setMonId("PES");
        detalle.setMonCotiz(1);
        return detalle;
    }

    private String formatearFecha (String fecha){
        String dia = fecha.substring(0,2);
        String mes = fecha.substring(3,5);
        String anio = fecha.substring(6);
        return anio+mes+dia;
    }

    private void grabarfactura(FacturaResponseDTO cae, FacturaDTO facturaDTO) {
        if (!cae.isError()){
//            articuloCService.descontarStock(facturaDTO);
            TipoComprobante tipoComprobante = tipoComprobanteRepository.findByCodigoAfip(facturaDTO.getTipoComprobante());
            SituacionesIVA situacionesIVA = situacionesIVARepository.findByCodigo(facturaDTO.getSituacionesIva());
            MovimientoCliente movimientoCliente = new MovimientoCliente();
            movimientoCliente.setFecha(formatearFecha(facturaDTO.getFecha()));
            movimientoCliente.setCodigoComprobante(tipoComprobante.getCodigo());
            movimientoCliente.setNumeroComprobante(cae.getNumeroComprobante());
            movimientoCliente.setTipoComprobante(tipoComprobante.getTipo());
            if (facturaDTO.getAfiliado()!=null) {
                movimientoCliente.setCliente(facturaDTO.getAfiliado());
                movimientoCliente.setQuienes("A");
            } else {
                movimientoCliente.setCliente(facturaDTO.getSindicato());
                movimientoCliente.setQuienes("S");
            }
            movimientoCliente.setSituacionIva(facturaDTO.getSituacionesIva());
            movimientoCliente.setFormaPago(facturaDTO.getCondicionesVenta());
            movimientoCliente.setExpreso(" ");
            movimientoCliente.setListaPrecio(facturaDTO.getListaPrecio());
            movimientoCliente.setBonificacion(facturaDTO.getBonificacion());
            movimientoCliente.setIva(0D);
            movimientoCliente.setIvaNoInsc(0D);
            if (tipoComprobante.getCodigo().equals("NCA") || tipoComprobante.getCodigo().equals("NCB") || tipoComprobante.getCodigo().equals("NCC")){
                movimientoCliente.setTotalComprobante(facturaDTO.getTotal()*-1);
            } else {
                movimientoCliente.setTotalComprobante(facturaDTO.getTotal());
            }
            movimientoCliente.setImpuestoPagado(0D);
            movimientoCliente.setAnulado("N");
            movimientoCliente.setLeyenda(facturaDTO.getLeyenda());
            movimientoCliente.setUsuarioAlta("Factura Electronica");
            movimientoCliente.setFechaAlta(new Date().toString());
            movimientoCliente.setCodigoComprobanteR(" ");
            movimientoCliente.setNumeroComprobanteR(" ");
            movimientoCliente.setNumeroComprobanteR(" ");
            movimientoCliente.setMotivoCD(" ");
            movimientoCliente.setOrganismo(facturaDTO.getSindicato());
            movimientoCliente.setPeriodo(formatearFecha(facturaDTO.getFecha()).substring(0,6));
            movimientoCliente.setNumeroCuota(0);
            movimientoCliente.setCuotas(0);
            movimientoCliente.setNumeroLiq(" ");
            movimientoCliente.setNumeroEnv(" ");
            movimientoCliente.setaDescuento("S");
            movimientoCliente.setOrdenServicio(0);
            movimientoCliente.setDelegacion(facturaDTO.getSindicato());
            movimientoCliente.setCodigoRechazo(" ");
            movimientoCliente.setCodigoFamilia("00");
            movimientoCliente.setOrdenFamilia(0);

            movimientoClienteRepository.save(movimientoCliente);
        }
    }

}
