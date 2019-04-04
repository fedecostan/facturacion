package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliarRepository extends JpaRepository<Familiar, Integer> {

    @Query(value = "SELECT * FROM familiares f" +
            " JOIN titularesa a ON f.nroregistro = a.nroregistro" +
            " WHERE a.delegacion = ?1 AND f.fechabaja = ?2" +
            " AND (f.orden = 1 or f.orden = 2)", nativeQuery = true)
    List<Familiar> findBySindicatoAndFechaBaja(String sindicato, String fechaBaja);

    @Query(value = "SELECT * FROM familiares f" +
            " WHERE f.nroregistro = ?1 AND f.orden = ?2", nativeQuery = true)
    Familiar findByNumeroRegistroAndOrden(String afiliado, int orden);
}
