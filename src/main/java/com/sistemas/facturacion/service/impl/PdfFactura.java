package com.sistemas.facturacion.service.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sistemas.facturacion.model.*;
import com.sistemas.facturacion.repository.*;
import com.sistemas.facturacion.service.dto.ArticuloFacturaDTO;
import com.sistemas.facturacion.service.dto.DatosFacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class PdfFactura {

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private SituacionesIVARepository situacionesIVARepository;

    @Autowired
    private DelegacionRepository delegacionRepository;

    @Autowired
    private LocalidadRepository localidadRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Autowired
    private CondicionVentaRepository condicionVentaRepository;

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private FamiliarRepository familiarRepository;

    @Value("${facturaTemplate}")
    private String facturaTemplate;

    @Value("${facturaOutput}")
    private String facturaOutput;

    @Value("${razonSocial}")
    private String razonSocial;

    @Value("${domicilioComercial}")
    private String domicilioComercial;

    @Value("${condicionFrenteIVA}")
    private String condicionFrenteIVA;

    @Value("${cuit}")
    private String cuit;

    @Value("${iibb}")
    private String iibb;

    @Value("${inicioActividad}")
    private String inicioActividad;

    @Value("${miMail}")
    private String miMail;

    @Value("${miMailPass}")
    private String miMailPass;

    public void imprimirFactura(DatosFacturaDTO datosFacturaDTO) {
        try {
            PdfReader reader = new PdfReader(facturaTemplate);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(facturaOutput));
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                TipoComprobante tipoComprobante = tipoComprobanteRepository.findByCodigoAfip(datosFacturaDTO.getFacturaDTO().getTipoComprobante());
                Empresa empresa = empresaRepository.findAll().get(0);
                SituacionesIVA situacionesIVA = situacionesIVARepository.findByCodigo(Integer.toString(empresa.getSituacionIva()));
                CondicionVenta condicionVenta = condicionVentaRepository.findByCodigo(datosFacturaDTO.getFacturaDTO().getCondicionesVenta());
                String cuitComprador;
                String razonSocialComprador;
                String ivaComprador;
                String domicilioComprador;
                String condicionVentaComprador;
                if (datosFacturaDTO.getFacturaDTO().getAfiliado()==null){
                    Delegacion delegacion = delegacionRepository.findByCodigo(datosFacturaDTO.getFacturaDTO().getSindicato());
                    cuitComprador = delegacion.getCuit();
                    razonSocialComprador = delegacion.getNombre();
                    ivaComprador = situacionesIVARepository.findByCodigo(delegacion.getSituacionIva()).getDescripcion();
                    Localidad localidad = localidadRepository.findByCodigo(delegacion.getLocalidad());
                    Provincia provincia = provinciaRepository.findByCodigo(localidad.getProvincia());
                    domicilioComprador = delegacion.getCalle()+" "+delegacion.getNumero()+" "+delegacion.getPiso()+" "+delegacion.getDepartamento()+" - "+localidad.getDescripcion()+" "+provincia.getDescripcion();
                    condicionVentaComprador = condicionVenta.getDescripcion();
                } else {
                    Titular titular = titularRepository.findByNumeroRegistro(datosFacturaDTO.getFacturaDTO().getAfiliado());
                    if (datosFacturaDTO.getFacturaDTO().getOrden()==0){
                        cuitComprador = titular.getCuil();
                        razonSocialComprador = titular.getApellidoYNombre();
                        ivaComprador = "IVA";//situacionesIVARepository.findByCodigo(titular.).getDescripcion();
                        Localidad localidad = localidadRepository.findByCodigo(titular.getLocalidad());
                        Provincia provincia = provinciaRepository.findByCodigo(localidad.getProvincia());
                        domicilioComprador = titular.getCalle()+" "+titular.getNumero()+" "+titular.getPiso()+" "+titular.getDepartamento()+" - "+localidad.getDescripcion()+" "+provincia.getDescripcion();
                        condicionVentaComprador = condicionVenta.getDescripcion();
                    } else {
                        Familiar familiar= familiarRepository.findByNumeroRegistroAndOrden(datosFacturaDTO.getFacturaDTO().getAfiliado(),datosFacturaDTO.getFacturaDTO().getOrden());
                        cuitComprador = familiar.getCuil();
                        razonSocialComprador = familiar.getApellidoYNombre();
                        ivaComprador = "IVA";//situacionesIVARepository.findByCodigo(delegacion.getSituacionIva()).getDescripcion();
                        Localidad localidad = localidadRepository.findByCodigo(titular.getLocalidad());
                        Provincia provincia = provinciaRepository.findByCodigo(localidad.getProvincia());
                        domicilioComprador = titular.getCalle()+" "+titular.getNumero()+" "+titular.getPiso()+" "+titular.getDepartamento()+" - "+localidad.getDescripcion()+" "+provincia.getDescripcion();
                        condicionVentaComprador = condicionVenta.getDescripcion();
                    }
                }
                PdfContentByte over = stamper.getOverContent(i);
                addText(tipoComprobante.getTipo(), 30, 285, 790, over, bf);
                addText("COD. "+datosFacturaDTO.getFacturaDTO().getTipoComprobante(), 10, 275, 775, over, bf);

                addText("Razón Social: "+empresa.getRazonSocial(), 10, 40, 735, over, bf);
                addText("Domicilio Comercial: "+empresa.getDomicilio(), 10, 40, 715, over, bf);
                addText("Condición frente al IVA: "+situacionesIVA.getDescripcion(), 10, 40, 695, over, bf);

                addText("FACTURA", 30, 370, 785, over, bf);
                addText("Comp. Nro: "+datosFacturaDTO.getFacturaResponseDTO().getNumeroComprobante(), 10, 450, 750, over, bf);
                addText("Punto de Venta: "+datosFacturaDTO.getFacturaDTO().getPuntoVenta(), 10, 330, 750, over, bf);
                addText("Fecha de Emisión: "+datosFacturaDTO.getFacturaDTO().getFecha(), 10, 330, 735, over, bf);
                addText("CUIT: "+empresa.getCuit(), 10, 330, 720, over, bf);
                addText("Ingresos Brutos: "+iibb, 10, 330, 705, over, bf);
                addText("Fecha de Inicio de Actividades: "+inicioActividad, 10, 330, 690, over, bf);

//                addText("Período Facturado Desde: "+datosFacturaDTO.getFacturaDTO().getFecha(), 10, 30, 662, over, bf);
//                addText("Hasta: "+datosFacturaDTO.getFacturaDTO().getFecha(), 10, 240, 662, over, bf);
//                addText("Fecha de Vto. para el Pago: "+datosFacturaDTO.getFacturaDTO().getFecha(), 10, 360, 662, over, bf);

                addText("CUIT: "+cuitComprador, 10, 40, 635, over, bf);
                addText("Apellido y Nombre / Razón Social: "+razonSocialComprador, 10, 270, 635, over, bf);
                addText("Condición frente al IVA: "+ivaComprador, 10, 40, 620, over, bf);
                addText("Domicilio Comercial: "+domicilioComprador, 10, 270, 620, over, bf);
                addText("Condición de Venta: "+condicionVentaComprador, 10, 40, 605, over, bf);

                addText("Código", 10, 20, 575, over, bf);
                addText("Producto / Servicio", 10, 60, 575, over, bf);
                addText("Cantidad", 10, 230, 575, over, bf);
                addText("Precio Unit.", 10, 280, 575, over, bf);
                addText("% Bonif.", 10, 340, 575, over, bf);
                addText("Imp Bonif", 10, 385, 575, over, bf);
                addText("Subtotal", 10, 510, 575, over, bf);

                int altura = 555;
                //DETALLE
                for (ArticuloFacturaDTO articuloFacturaDTO: datosFacturaDTO.getFacturaDTO().getArticulos()) {
                    Double precio = articuloFacturaDTO.getPrecio()*articuloFacturaDTO.getCantidad();
                    addText(articuloFacturaDTO.getCodigo(), 10, 28, altura, over, bf);
                    addText(articuloFacturaDTO.getDescripcion(), 10, 65, altura, over, bf);
                    addText(articuloFacturaDTO.getCantidad()+"", 10, 250, altura, over, bf);
                    addText(""+articuloFacturaDTO.getPrecio(), 10, 295, altura, over, bf);
                    addText(datosFacturaDTO.getFacturaDTO().getBonificacion()+"", 10, 355, altura, over, bf);
                    addText(""+(precio-precio*datosFacturaDTO.getFacturaDTO().getBonificacion()/100), 10, 395, altura, over, bf);
                    addText(""+precio*precio*datosFacturaDTO.getFacturaDTO().getBonificacion()/100, 10, 460, altura, over, bf);
                    addText(""+(precio-precio*datosFacturaDTO.getFacturaDTO().getBonificacion()/100), 10, 535, altura, over, bf);
                    altura = altura - 15;
                }

                addText("Subtotal: $"+datosFacturaDTO.getFacturaDTO().getTotal(), 10, 350, 220, over, bf);
                addText("Importe otros Tributos: $0", 10, 350, 205, over, bf);
                addText("Importe Total: $"+datosFacturaDTO.getFacturaDTO().getTotal(), 10, 350, 190, over, bf);

                addText("CAE Nº "+datosFacturaDTO.getFacturaResponseDTO().getCAE(), 12, 350, 150, over, bf);
                addText("Fecha de Vto. de CAE: "+datosFacturaDTO.getFacturaResponseDTO().getFechaVencimiento(), 12, 350, 130, over, bf);

                BarraGenerator bg = new BarraGenerator();
                BufferedImage bufferedImage = bg.dameImagen(cuit, Long.parseLong(datosFacturaDTO.getFacturaResponseDTO().getCAE()), Integer.parseInt(datosFacturaDTO.getFacturaDTO().getPuntoVenta()), datosFacturaDTO.getFacturaResponseDTO().getFechaVencimiento(), tipoComprobante.getCodigo());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                Image img = Image.getInstance(baos.toByteArray());
                img.setAbsolutePosition(16, 80);
                img.scalePercent(52);
                over.addImage(img);
            }
            stamper.close();
            if (datosFacturaDTO.getFacturaDTO().getMail()!=null && !datosFacturaDTO.getFacturaDTO().getMail().equals("")) sendMail(datosFacturaDTO.getFacturaDTO().getMail(),stamper);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void addText(String text, int size, int x, int y, PdfContentByte over, BaseFont bf) {
        over.beginText();
        over.setFontAndSize(bf, size);
        over.setTextMatrix(x, y);
        over.showText(text);
        over.endText();
    }

    private void sendMail(String mail, PdfStamper stamper) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        try {
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(miMail, miMailPass);
                        }
                    });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(miMail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                message.setSubject("Factura Electrónica");

                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setText("Mail enviado automáticamente por Factura Electrónica. No responda éste mail.");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(bodyPart);
                bodyPart = new MimeBodyPart();
                DataSource dataSource = new FileDataSource(facturaOutput);
                bodyPart.setDataHandler(new DataHandler(dataSource));
                bodyPart.setFileName("facturaEjemplo.pdf");
                multipart.addBodyPart(bodyPart);
                message.setContent(multipart);

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
