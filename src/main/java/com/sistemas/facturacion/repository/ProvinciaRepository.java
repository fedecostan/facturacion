package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, String> {
    Provincia findByCodigo(String provincia);
}