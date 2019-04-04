package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, String> {
    Localidad findByCodigo(String localidad);
}