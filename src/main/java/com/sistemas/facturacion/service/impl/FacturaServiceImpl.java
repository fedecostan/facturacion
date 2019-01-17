package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.model.Autorizacion;
import com.sistemas.facturacion.repository.AutorizacionRepository;
import com.sistemas.facturacion.service.FacturaService;
import com.sistemas.facturacion.service.afip.LoginCMS;
import com.sistemas.facturacion.service.afip.LoginCMSService;
import com.sistemas.facturacion.service.afipFac.FEAuthRequest;
import com.sistemas.facturacion.service.afipFac.FECAERequest;
import com.sistemas.facturacion.service.afipFac.FECAEResponse;
import com.sistemas.facturacion.service.afipFac.ServiceSoap;
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
    public void generarFactura() {
        Autorizacion autorizacion = autorizacionRepository.findFirstByOrderByIdDesc();
        try {
            String factura = solicitarCae(autorizacion);
        } catch (Exception e) {
            try {
                System.out.println("OBTENIENDO AUTORIZACION");
                autorizacion = obtenerAutorizacion();
                String factura = solicitarCae(autorizacion);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private String solicitarCae(Autorizacion autorizacion) throws Exception{
        if (autorizacion==null) throw new Exception();
        ServiceSoap serviceSoap = new com.sistemas.facturacion.service.afipFac.Service().getServiceSoap();
        FEAuthRequest feAuthRequest = new FEAuthRequest();
        feAuthRequest.setToken(autorizacion.getToken());
        feAuthRequest.setSign(autorizacion.getSign());
        feAuthRequest.setCuit(33693450239L);
        FECAERequest fecaeRequest = new FECAERequest();
        FECAEResponse s = serviceSoap.fecaeSolicitar(feAuthRequest,fecaeRequest);
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
