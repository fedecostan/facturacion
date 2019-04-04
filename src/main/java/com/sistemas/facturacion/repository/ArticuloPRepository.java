package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.ArticuloP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloPRepository extends JpaRepository<ArticuloP, Long> {

    @Query(value = "SELECT * FROM articulop a" +
            " WHERE a.rubroarticulo = ?1" +
            " AND a.fechadesde <= ?2" +
            " AND a.fechahasta >= ?2", nativeQuery = true)
    ArticuloP findByRubroArticuloAndFecha(String rubroArticulo, String fecha);

}
