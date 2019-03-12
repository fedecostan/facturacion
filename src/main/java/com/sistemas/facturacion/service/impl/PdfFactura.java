package com.sistemas.facturacion.service.impl;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfFactura {

    public static void main(String[] args){
        try {
            imprimirFactura();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void imprimirFactura() throws IOException, DocumentException {
        PdfReader reader = new PdfReader("/home/fcostantino/Workspace/PROYECTO/facturacion/src/main/resources/facturaTemplate.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("/home/fcostantino/Workspace/PROYECTO/facturaEjemplo.pdf"));
        BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        for (int i=1; i<=reader.getNumberOfPages(); i++){
            PdfContentByte over = stamper.getOverContent(i);
            addText("X",30,287,799, over, bf);
            addText("COD. XX",10,280,785, over, bf);

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
