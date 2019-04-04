package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.MovimientoCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoClienteRepository extends JpaRepository<MovimientoCliente, Long> {
}
