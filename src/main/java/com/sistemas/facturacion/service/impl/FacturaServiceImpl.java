package com.sistemas.facturacion.service.impl;

import com.sistemas.facturacion.service.afip.LoginCMS;
import com.sistemas.facturacion.service.afip.LoginCMSService;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import org.apache.axis.encoding.Base64;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignedDataGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertStore;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

//@Service
public class FacturaServiceImpl {//implements FacturaService {

    public static void main(String[] args){
        try {
            generarFactura();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
    public static void generarFactura() throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException {

        PrivateKey pKey = null;
        X509Certificate pCertificate = null;
        byte [] asn1_cms = null;
        CertStore cstore = null;
        String LoginTicketRequest_xml;
        String SignerDN = null;
        try {
            KeyStore ks = KeyStore.getInstance("pkcs12");
            FileInputStream p12stream = new FileInputStream ("/home/fcostantino/Downloads/wsaa_client_java/PRUEBA/nuevoElisaConServicioAsociado.p12") ;
            ks.load(p12stream, "1234".toCharArray());
            p12stream.close();
            pKey = (PrivateKey) ks.getKey("fede", "1234".toCharArray());
            pCertificate = (X509Certificate)ks.getCertificate("fede");
            SignerDN = pCertificate.getSubjectDN().toString();
            ArrayList<X509Certificate> certList = new ArrayList<>();
            certList.add(pCertificate);
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new BouncyCastleProvider());
            }
            cstore = CertStore.getInstance("Collection", new CollectionCertStoreParameters(certList), "BC");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //---------------------------
        Date GenTime = new Date();
        GregorianCalendar gentime = new GregorianCalendar();
        GregorianCalendar exptime = new GregorianCalendar();
        String UniqueId = new Long(GenTime.getTime() / 1000).toString();

        exptime.setTime(new Date(GenTime.getTime()+36000L));

        XMLGregorianCalendarImpl XMLGenTime = new XMLGregorianCalendarImpl(gentime);
        XMLGregorianCalendarImpl XMLExpTime = new XMLGregorianCalendarImpl(exptime);

        LoginTicketRequest_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                +"<loginTicketRequest version=\"1.0\">"
                +"<header>"
                +"<source>" + SignerDN + "</source>"
                +"<destination>" + "cn=wsaahomo,o=afip,c=ar,serialNumber=CUIT 33693450239" + "</destination>"
                +"<uniqueId>" + UniqueId + "</uniqueId>"
                +"<generationTime>" + XMLGenTime + "</generationTime>"
                +"<expirationTime>" + XMLExpTime + "</expirationTime>"
                +"</header>"
                //+"<service>" + "test" + "</service>"
                +"<service>" + "wsfe" + "</service>"
                +"</loginTicketRequest>";
        //---------------------------
        try {
            CMSSignedDataGenerator gen = new CMSSignedDataGenerator();
            gen.addSigner(pKey, pCertificate, CMSSignedDataGenerator.DIGEST_SHA1);
            gen.addCertificatesAndCRLs(cstore);
            CMSProcessable data = new CMSProcessableByteArray(LoginTicketRequest_xml.getBytes());
            CMSSignedData signed = gen.generate(data, true, "BC");
            asn1_cms = signed.getEncoded();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String LoginTicketResponse = invoke_wsaa(asn1_cms, "http://wsaahomo.afip.gov.ar/ws/services/LoginCms");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String invoke_wsaa(byte[] asn1_cms, String endpoint) {
        String LoginTicketResponse = null;
        try {
            LoginCMS loginCMS = new LoginCMSService().getLoginCms();
            System.out.println(loginCMS.loginCms(Base64.encode(asn1_cms)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (LoginTicketResponse);
    }

}
