package com.sistemas.facturacion.model;

public enum Comprobantes {
    FACTURAS_A(1, "Facturas A"),
    NOTAS_DE_DEBITO_A(2, "Notas de débito A"),
    NOTAS_DE_CREDITO_A(3, "Notas de crédito A"),
    RECIBOS_A(4, "Recibos A"),
    NOTAS_DE_VENTA_AL_CONTADO_A(5, "Notas de venta al contado A"),

    FACTURAS_B(6, "Facturas B"),
    NOTAS_DE_DEBITO_B(7, "Notas de débito B"),
    NOTAS_DE_CREDITO_B(8, "Notas de crédito B"),
    RECIBOS_B(9, "Recibos B"),
    NOTAS_DE_VENTA_AL_CONTADO_B(10, "Notas de venta al contado B"),

    FACTURAS_C(11, "Facturas C"),
    NOTAS_DE_DEBITO_C(12, "Notas de débito C"),
    NOTAS_DE_CREDITO_C(13, "Notas de crédito C"),
    RECIBOS_C(15, "Recibos C"),
    NOTAS_DE_VENTA_AL_CONTADO_C(16, "Notas de venta al contado C"),

    FACTURAS_DE_EXPORTACION(19, "Facturas de exportación"),
    NOTAS_DE_DEBITO_PARA_EXPORTACION(20, "Notas de débito p/ops. con el ext."),
    NOTAS_DE_CREDITO_PARA_EXPORTACION(21, "Notas de crédito p/ops. con el ext."),
    MANDATO_CONSIGNACION(31, "Mandato/Consignación"),

    FACTURAS_M(51, "Facturas M"),
    NOTAS_DE_DEBITO_M(52, "Notas de débito M"),
    NOTAS_DE_CREDITO_M(53, "Notas de crédito M"),
    RECIBOS_M(54, "Recibos M"),
    NOTAS_DE_VENTA_AL_CONTADO_M(55, "Notas de venta al contado M"),
    REMITO_R(91, "Remito R");

    Comprobantes(final int codigo, final String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    private final int codigo;
    private final String descripcion;

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
