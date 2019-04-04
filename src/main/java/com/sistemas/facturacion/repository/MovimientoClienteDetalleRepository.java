package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.MovimientoClienteDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoClienteDetalleRepository extends JpaRepository<MovimientoClienteDetalle, Long> {
}
