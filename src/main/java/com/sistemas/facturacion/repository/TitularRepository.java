package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {

    @Query(value = "SELECT * FROM titulares t" +
            " JOIN titularesa a ON t.nroregistro = a.nroregistro" +
            " WHERE a.delegacion = ?1 AND a.fechabaja = ?2", nativeQuery = true)
    List<Titular> findByDelegacionAndFechaBaja(String sindicato, String fechaBaja);

    @Query(value = "SELECT * FROM titulares t" +
            " JOIN titularesa a ON t.nroregistro = a.nroregistro" +
            " WHERE a.delegacion = ?1" +
            " AND t.nroregistro = ?2" +
            " AND a.fechabaja = ?3", nativeQuery = true)
    Titular findByDelegacionAndNumeroRegistroAndFechaBaja(String formattedSindicato, String formattedTitular, String fechaBaja);

    @Query(value = "SELECT * FROM titulares t" +
            " WHERE t.nroregistro = ?1", nativeQuery = true)
    Titular findByNumeroRegistro(String afiliado);
}
