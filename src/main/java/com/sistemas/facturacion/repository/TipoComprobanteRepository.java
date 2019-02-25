package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoComprobanteRepository extends JpaRepository<TipoComprobante, Long> {

    TipoComprobante findByCodigoAfip(Integer codigoAfip);

    @Query(value = "SELECT * FROM tcomprob", nativeQuery = true)
    List<TipoComprobante> findTodos();
}
