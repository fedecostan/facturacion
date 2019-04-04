package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.SituacionesIVA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SituacionesIVARepository extends JpaRepository<SituacionesIVA, Long> {
    SituacionesIVA findByCodigo(String situacionesIva);

    @Query(value = "SELECT * FROM tsitiva t" +
            " WHERE t.codigoafip IS NOT NULL", nativeQuery = true)
    List<SituacionesIVA> findTodos();
}
