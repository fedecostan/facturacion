package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, String> {
    TipoDocumento findByCodigo(String tipoDocumento);
}
