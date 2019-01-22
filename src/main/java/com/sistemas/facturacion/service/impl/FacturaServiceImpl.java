package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Autorizacion;
import com.sistemas.facturacion.repository.AutorizacionRepository;
import com.sistemas.facturacion.service.FacturaService;
import com.sistemas.facturacion.service.afip.LoginCMS;
import com.sistemas.facturacion.service.afip.LoginCMSService;
import com.sistemas.facturacion.service.afipFac.*;
import com.sistemas.facturacion.service.dto.FacturaDTO;
import org.apache.axis.encoding.Base64;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertStore;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Override
    public String generarFactura(FacturaDTO facturaDTO) {
        Autorizacion autorizacion = autorizacionRepository.findFirstByOrderByIdDesc();
        try {
            String factura = solicitarCae(autorizacion, facturaDTO);
        } catch (Exception e) {
            try {
                System.out.println("OBTENIENDO AUTORIZACION");
                autorizacion = obtenerAutorizacion();
                String factura = solicitarCae(autorizacion, facturaDTO);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return "";
    }

    private String solicitarCae(Autorizacion autorizacion, FacturaDTO facturaDTO) throws Exception{
        if (autorizacion==null) throw new Exception();
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FEAuthRequest autenticacion = new FEAuthRequest();
        autenticacion.setToken(autorizacion.getToken());
        autenticacion.setSign(autorizacion.getSign());
        autenticacion.setCuit(27149025893L);
        FECAERequest request = new FECAERequest();
        FECAECabRequest cabecera = new FECAECabRequest();
        cabecera.setCantReg(1);
        cabecera.setPtoVta(30); //Obtener de factura
        cabecera.setCbteTipo(1); //Obtener de factura
        request.setFeCabReq(cabecera);
        ArrayOfFECAEDetRequest arrayOfFECAEDetRequest = new ArrayOfFECAEDetRequest();
        FECAEDetRequest detalle = new FECAEDetRequest();
        detalle.setConcepto(2); //1- Producto 2- Servicio 3- Producto y Servicio
        detalle.setDocTipo(80); //Obtener del afiliado
        detalle.setDocNro(30663791377L); //Obtener del afiliado
        detalle.setCbteDesde(2); //Ultimo comprobante + 1
        detalle.setCbteHasta(2); //Ultimo comprobante + 1
        detalle.setCbteFch("20190122"); //Fecha comprobante
        detalle.setImpTotal(1000); //Total a facturar
        detalle.setImpTotConc(0); //Concepto no gravado
        detalle.setImpNeto(826.44); //Importe neto sin IVA
        detalle.setImpOpEx(0); //Excentas siempre 0
        detalle.setImpIVA(173.56);
        detalle.setImpTrib(0);
        detalle.setFchServDesde("20190122"); //NO ES OBLIGATORIO
        detalle.setFchServHasta("20190122"); //NO ES OBLIGATORIO
        detalle.setFchVtoPago("20190122"); //NO ES OBLIGATORIO
        detalle.setMonId("PES"); //CODIGO MONEDA
        detalle.setMonCotiz(1); //COTIZACION MONEDA
        // detalle.setCbtesAsoc(); //NO ES OBLIGATORIO
        // detalle.setTributos(); //NO ES OBLIGATORIO
        ArrayOfAlicIva arrayOfAlicIva = new ArrayOfAlicIva();
        AlicIva alicIva = new AlicIva();
        alicIva.setBaseImp(826.44);
        alicIva.setImporte(173.56);
        alicIva.setId(5);
        arrayOfAlicIva.getAlicIva().add(alicIva);
        detalle.setIva(arrayOfAlicIva); //NO ES OBLIGATORIO
        // detalle.setOpcionales(); //NO ES OBLIGATORIO
        // detalle.setCompradores(); //NO ES OBLIGATORIO
        arrayOfFECAEDetRequest.getFECAEDetRequest().add(detalle);
        request.setFeDetReq(arrayOfFECAEDetRequest);
        FECAEResponse s = serviceSoap.fecaeSolicitar(autenticacion,request);
        System.out.println(s);
        if (s.getErrors().getErr().get(0).getCode() == 600) throw new Exception();
        return s.toString();
    }

    public Autorizacion obtenerAutorizacion() throws Exception {
        KeyStore ks = KeyStore.getInstance("pkcs12");
        FileInputStream p12stream = new FileInputStream("/home/fcostantino/Downloads/wsaa_client_java/PRUEBA/nuevoElisaConServicioAsociado.p12");
        ks.load(p12stream, "1234".toCharArray());
        p12stream.close();
        PrivateKey pKey = (PrivateKey) ks.getKey("fede", "1234".toCharArray());
        X509Certificate pCertificate = (X509Certificate) ks.getCertificate("fede");
        String signerDN = pCertificate.getSubjectDN().toString();
        ArrayList<X509Certificate> certList = new ArrayList<>();
        certList.add(pCertificate);
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
        CertStore cstore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");
        Date date = new Date();
        GregorianCalendar gentime = new GregorianCalendar();
        GregorianCalendar exptime = new GregorianCalendar();
        String uniqueId = new Long(date.getTime() / 1000).toString();
        exptime.setTime(new Date(date.getTime() + 36000L));
        XMLGregorianCalendar xmlGenTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(gentime);
        XMLGregorianCalendar xmlExpTime = DatatypeFactory.newInstance().newXMLGregorianCalendar(exptime);
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<loginTicketRequest version=\"1.0\">"
                + "<header>"
                + "<source>" + signerDN + "</source>"
                + "<destination>" + "cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239" + "</destination>"
                + "<uniqueId>" + uniqueId + "</uniqueId>"
                + "<generationTime>" + xmlGenTime + "</generationTime>"
                + "<expirationTime>" + xmlExpTime + "</expirationTime>"
                + "</header>"
                + "<service>" + "wsfe" + "</service>"
                + "</loginTicketRequest>";
        CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
        gen.addSigner(pKey, pCertificate, CMSSignedDataGenerator.DIGEST_SHA1);
        gen.addCertificatesAndCRLs(cstore);
        CMSProcessable data = new CMSProcessableByteArray(request.getBytes());
        CMSSignedData signed = gen.generate(data, true, "BC");
        byte[] asn1_cms = signed.getEncoded();
        String response = invoke_wsaa(asn1_cms);
        System.out.println(response);
        Autorizacion autorizacion = new Autorizacion();
        autorizacion.setToken(response.substring(response.indexOf("<token>")+7,response.indexOf("</token>")));
        autorizacion.setSign(response.substring(response.indexOf("<sign>")+6,response.indexOf("</sign>")));
        autorizacion.setFechaGeneracion(new Date());
        autorizacionRepository.save(autorizacion);
        return autorizacion;
    }

    private String invoke_wsaa(byte[] asn1_cms) throws Exception {
        String LoginTicketResponse = null;
        LoginCMS loginCMS = new LoginCMSService().getLoginCms();
        LoginTicketResponse = loginCMS.loginCms(Base64.encode(asn1_cms));
        return (LoginTicketResponse);
    }

}
