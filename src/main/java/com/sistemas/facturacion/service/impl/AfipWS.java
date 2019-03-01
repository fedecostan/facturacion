package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Autorizacion;
import com.sistemas.facturacion.repository.AutorizacionRepository;
import com.sistemas.facturacion.service.afip.LoginCMS;
import com.sistemas.facturacion.service.afip.LoginCMSService;
import com.sistemas.facturacion.service.afipFac.*;
import com.sistemas.facturacion.service.dto.FacturaDTO;
import com.sistemas.facturacion.service.dto.FacturaResponseDTO;
import org.apache.axis.encoding.Base64;
import org.bouncycastle.cert.jcajce.JcaCertStore;
import org.bouncycastle.cms.*;
import org.bouncycastle.cms.jcajce.JcaSignerInfoGeneratorBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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

public class AfipWS {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Value("${cert.name}")
    private String name;

    @Value("${cert.pass}")
    private String password;

    @Value("${cert.path}")
    private String path;

    @Value("${cuit}")
    private Long cuit;

    public Autorizacion obtenerAutorizacion() throws Exception {
        KeyStore ks = KeyStore.getInstance("pkcs12");
        FileInputStream p12stream = new FileInputStream(path);
        ks.load(p12stream, password.toCharArray());
        p12stream.close();
        PrivateKey pKey = (PrivateKey) ks.getKey(name, password.toCharArray());
        X509Certificate pCertificate = (X509Certificate) ks.getCertificate(name);
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
        ContentSigner sha1Signer = new JcaContentSignerBuilder("SHA1withRSA").build(pKey);
        gen.addSignerInfoGenerator(new JcaSignerInfoGeneratorBuilder(new JcaDigestCalculatorProviderBuilder().setProvider("BC").build()).build(sha1Signer, pCertificate));
        gen.addCertificates(new JcaCertStore(certList));
        CMSProcessable data = new CMSProcessableByteArray(request.getBytes());
        CMSTypedData msg = new CMSProcessableByteArray(request.getBytes());
        CMSSignedData sigData = gen.generate(msg, true);
        byte[] encodedBytes = sigData.getEncoded();
        Autorizacion autorizacion = new Autorizacion();
        try {
            String response = invoke_wsaa(encodedBytes);
            System.out.println(response);
            autorizacion.setToken(response.substring(response.indexOf("<token>") + 7, response.indexOf("</token>")));
            autorizacion.setSign(response.substring(response.indexOf("<sign>") + 6, response.indexOf("</sign>")));
            autorizacion.setFechaGeneracion(new Date());
            autorizacionRepository.save(autorizacion);
        } catch (Exception e) {
            e.printStackTrace();
            autorizacion = autorizacionRepository.findFirstByOrderByIdDesc();
        }
        return autorizacion;
    }

    private String invoke_wsaa(byte[] asn1_cms) throws Exception {
        String LoginTicketResponse = null;
        LoginCMS loginCMS = new LoginCMSService().getLoginCms();
        LoginTicketResponse = loginCMS.loginCms(Base64.encode(asn1_cms));
        return (LoginTicketResponse);
    }

    public Long obtenerUltimoComprobante(Autorizacion autorizacion, FacturaDTO facturaDTO) {
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FEAuthRequest autenticacion = new FEAuthRequest();
        autenticacion.setToken(autorizacion.getToken());
        autenticacion.setSign(autorizacion.getSign());
        autenticacion.setCuit(cuit);
        FERecuperaLastCbteResponse feRecuperaLastCbteResponse = serviceSoap.feCompUltimoAutorizado(autenticacion,Integer.parseInt(facturaDTO.getPuntoVenta()),Integer.parseInt(facturaDTO.getTipoComprobante()));
        return new Long(feRecuperaLastCbteResponse.getCbteNro());
    }

    public FacturaResponseDTO generarFactura(FEAuthRequest autenticacion, FECAERequest request) throws Exception{
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FECAEResponse response = serviceSoap.fecaeSolicitar(autenticacion,request);
        FacturaResponseDTO facturaResponseDTO = new FacturaResponseDTO();
        facturaResponseDTO.setCAE(response.getFeDetResp().getFECAEDetResponse().get(0).getCAE());
        facturaResponseDTO.setNumeroComprobante(Long.toString(request.getFeDetReq().getFECAEDetRequest().get(0).getCbteDesde()));
        facturaResponseDTO.setFechaVencimiento(response.getFeDetResp().getFECAEDetResponse().get(0).getCAEFchVto());
        facturaResponseDTO.setError(facturaResponseDTO.getCAE()==null);
        return facturaResponseDTO;
    }

}
