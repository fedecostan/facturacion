package com.sistemas.facturacion.service.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.sistemas.facturacion.model.TipoComprobante;
import com.sistemas.facturacion.repository.TipoComprobanteRepository;
import com.sistemas.facturacion.service.dto.ArticuloFacturaDTO;
import com.sistemas.facturacion.service.dto.DatosFacturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PdfFactura {

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

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

    public void imprimirFactura(DatosFacturaDTO datosFacturaDTO, String mail) {
        try {
            PdfReader reader = new PdfReader(facturaTemplate);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(facturaOutput));
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                TipoComprobante tipoComprobante = tipoComprobanteRepository.findByCodigoAfip(datosFacturaDTO.getFacturaDTO().getTipoComprobante());
                PdfContentByte over = stamper.getOverContent(i);
                addText(tipoComprobante.getTipo(), 30, 285, 790, over, bf);
                addText("COD. "+datosFacturaDTO.getFacturaDTO().getTipoComprobante(), 10, 275, 775, over, bf);

                addText("Razón Social: "+razonSocial, 10, 40, 735, over, bf);
                addText("Domicilio Comercial: "+domicilioComercial, 10, 40, 715, over, bf);
                addText("Condición frente al IVA: "+condicionFrenteIVA, 10, 40, 695, over, bf);

                addText("FACTURA", 30, 370, 785, over, bf);
                addText("Comp. Nro: "+datosFacturaDTO.getFacturaResponseDTO().getNumeroComprobante(), 10, 450, 750, over, bf);
                addText("Punto de Venta: "+datosFacturaDTO.getFacturaDTO().getPuntoVenta(), 10, 330, 750, over, bf);
                addText("Fecha de Emisión: "+datosFacturaDTO.getFacturaDTO().getFecha(), 10, 330, 735, over, bf);
                addText("CUIT: "+cuit, 10, 330, 720, over, bf);
                addText("Ingresos Brutos: "+iibb, 10, 330, 705, over, bf);
                addText("Fecha de Inicio de Actividades: "+inicioActividad, 10, 330, 690, over, bf);

                addText("Período Facturado Desde: DD/MM/AAAA", 10, 30, 662, over, bf);
                addText("Hasta: DD/MM/AAAA", 10, 240, 662, over, bf);
                addText("Fecha de Vto. para el Pago: DD/MM/AAAA", 10, 360, 662, over, bf);

                addText("CUIT: XX-XXXXXXXX-X", 10, 40, 635, over, bf);
                addText("Apellido y Nombre / Razón Social: XXXX XXXX", 10, 270, 635, over, bf);
                addText("Condición frente al IVA: XXXX XXXX XXXX", 10, 40, 620, over, bf);
                addText("Domicilio Comercial: XXXX XXXX", 10, 270, 620, over, bf);
                addText("Condición de Venta: XXXX", 10, 40, 605, over, bf);

                addText("Código", 10, 20, 575, over, bf);
                addText("Producto / Servicio", 10, 60, 575, over, bf);
                addText("Cantidad", 10, 230, 575, over, bf);
                addText("Precio Unit.", 10, 280, 575, over, bf);
                addText("% Bonif.", 10, 340, 575, over, bf);
                addText("Subtotal", 10, 385, 575, over, bf);
                addText("Alicuota IVA", 10, 435, 575, over, bf);
                addText("Subtotal c/IVA", 10, 510, 575, over, bf);

                int altura = 555;
                //DETALLE
                for (ArticuloFacturaDTO articuloFacturaDTO: datosFacturaDTO.getFacturaDTO().getArticulos()) {
                    addText(articuloFacturaDTO.getCodigo(), 10, 28, altura, over, bf);
                    addText(articuloFacturaDTO.getDescripcion(), 10, 65, altura, over, bf);
                    addText(articuloFacturaDTO.getCantidad()+"", 10, 250, altura, over, bf);
                    addText("$"+articuloFacturaDTO.getPrecio(), 10, 295, altura, over, bf);
                    addText(datosFacturaDTO.getFacturaDTO().getBonificacion()+"", 10, 355, altura, over, bf);
                    addText("$"+articuloFacturaDTO.getPrecio()*articuloFacturaDTO.getCantidad()*datosFacturaDTO.getFacturaDTO().getBonificacion()/100, 10, 395, altura, over, bf);
                    addText("21%", 10, 460, altura, over, bf);
                    addText("$"+articuloFacturaDTO.getTotal()*datosFacturaDTO.getFacturaDTO().getBonificacion()/100, 10, 535, altura, over, bf);
                    altura = altura - 15;
                }

                addText("Subtotal: $"+datosFacturaDTO.getFacturaDTO().getTotal(), 10, 350, 220, over, bf);
                addText("Importe otros Tributos: $0", 10, 350, 205, over, bf);
                addText("Importe Total: $"+datosFacturaDTO.getFacturaDTO().getTotal(), 10, 350, 190, over, bf);

                addText("CAE Nº "+datosFacturaDTO.getFacturaResponseDTO().getCAE(), 12, 350, 150, over, bf);
                addText("Fecha de Vto. de CAE: "+datosFacturaDTO.getFacturaResponseDTO().getFechaVencimiento(), 12, 350, 130, over, bf);

                BarraGenerator bg = new BarraGenerator();
                BufferedImage bufferedImage = bg.dameImagen(cuit, Long.parseLong(datosFacturaDTO.getFacturaResponseDTO().getCAE()), Integer.parseInt(datosFacturaDTO.getFacturaDTO().getPuntoVenta()), datosFacturaDTO.getFacturaDTO().getFecha(), tipoComprobante.getCodigo());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", baos);
                Image img = Image.getInstance(baos.toByteArray());
                img.setAbsolutePosition(16, 80);
                img.scalePercent(52);
                over.addImage(img);
            }
            stamper.close();
            if (mail!=null && !mail.equals("")) sendMail(mail);
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

    private void sendMail(String mail) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
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
            message.setText("");
//            message.set
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
