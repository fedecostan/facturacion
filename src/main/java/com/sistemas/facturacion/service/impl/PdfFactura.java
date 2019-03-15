package com.sistemas.facturacion.service.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfFactura {

    public void imprimirFactura() throws IOException, DocumentException {
        PdfReader reader = new PdfReader("/home/fcostantino/Workspace/PROYECTO/facturacion/src/main/resources/facturaTemplate.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("/home/fcostantino/Workspace/PROYECTO/facturaEjemplo.pdf"));
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        for (int i=1; i<=reader.getNumberOfPages(); i++){
            PdfContentByte over = stamper.getOverContent(i);
            addText("X",30,285,790, over, bf);
            addText("COD. XX",10,275,775, over, bf);

            addText("Razón Social: XXXX XXXX",10,40,735, over, bf);
            addText("Domicilio Comercial: XXXX XXXX",10,40,715, over, bf);
            addText("Condición frente al IVA: XXXX XXXX XXXX",10,40,695, over, bf);

            addText("FACTURA",30,370,785, over, bf);
            addText("Comp. Nro: XXXXXXXX",10,450,750, over, bf);
            addText("Punto de Venta: XX",10,330,750, over, bf);
            addText("Fecha de Emisión: DD/MM/AAAA",10,330,735, over, bf);
            addText("CUIT: XX-XXXXXXXX-X",10,330,720, over, bf);
            addText("Ingresos Brutos: XXXXXX-XX",10,330,705, over, bf);
            addText("Fecha de Inicio de Actividades: DD/MM/AAAA",10,330,690, over, bf);

            addText("Período Facturado Desde: DD/MM/AAAA",10,30,662, over, bf);
            addText("Hasta: DD/MM/AAAA",10,240,662, over, bf);
            addText("Fecha de Vto. para el Pago: DD/MM/AAAA",10,360,662, over, bf);

            addText("CUIT: XX-XXXXXXXX-X",10,40,635, over, bf);
            addText("Apellido y Nombre / Razón Social: XXXX XXXX",10,270,635, over, bf);
            addText("Condición frente al IVA: XXXX XXXX XXXX",10,40,620, over, bf);
            addText("Domicilio Comercial: XXXX XXXX",10,270,620, over, bf);
            addText("Condición de Venta: XXXX",10,40,605, over, bf);

            addText("Código",10,20,575, over, bf);
            addText("Producto / Servicio",10,60,575, over, bf);
            addText("Cantidad",10,230,575, over, bf);
            addText("Precio Unit.",10,280,575, over, bf);
            addText("% Bonif.",10,340,575, over, bf);
            addText("Subtotal",10,385,575, over, bf);
            addText("Alicuota IVA",10,435,575, over, bf);
            addText("Subtotal c/IVA",10,510,575, over, bf);

            int altura = 555;
            //DETALLE
            for (int j=1; j<11; j++){
                addText(""+j,10,28,altura, over, bf);
                addText("Producto "+j,10,65,altura, over, bf);
                addText("X",10,250,altura, over, bf);
                addText("$ X",10,295,altura, over, bf);
                addText("X",10,355,altura, over, bf);
                addText("$ X",10,395,altura, over, bf);
                addText("XX%",10,460,altura, over, bf);
                addText("$ X",10,535,altura, over, bf);
                altura = altura - 15;
            }

            addText("Subtotal: $ X",10,350,220, over, bf);
            addText("Importe otros Tributos: $ X",10,350,205, over, bf);
            addText("Importe Total: $ X",10,350,190, over, bf);

            addText("CAE Nº XXXXXXXXXXXXXX",12,350,150, over, bf);
            addText("Fecha de Vto. de CAE: DD/MM/AAAA",12,350,130, over, bf);

            BarraGenerator bg = new BarraGenerator();
            BufferedImage bufferedImage = bg.dameImagen("37381579906",12345678912345L,20,"15/03/2019","FCB");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            Image img = Image.getInstance(baos.toByteArray());
            img.setAbsolutePosition(16,80);
            img.scalePercent(52);
            over.addImage(img);
        }
        stamper.close();
    }

    private static void addText(String text, int size, int x, int y, PdfContentByte over, BaseFont bf){
        over.beginText();
        over.setFontAndSize(bf, size);
        over.setTextMatrix(x, y);
        over.showText(text);
        over.endText();
    }

}
