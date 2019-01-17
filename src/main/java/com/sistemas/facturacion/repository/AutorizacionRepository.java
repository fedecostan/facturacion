package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Autorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long> {

    Autorizacion findFirstByOrderByIdDesc();
}
