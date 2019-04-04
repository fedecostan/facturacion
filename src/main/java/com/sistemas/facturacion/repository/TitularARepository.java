package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.TitularA;
import com.sistemas.facturacion.model.TitularAId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TitularARepository extends JpaRepository<TitularA, TitularAId> {

    @Query(value = "SELECT * FROM titularesa a" +
            " WHERE a.nroregistro = ?1 AND a.fechabaja = ?2", nativeQuery = true)
    TitularA findByNroRegistroAndFechaBaja(String numeroRegistro, String fechaBaja);
    
}
