package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.ArticuloC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloCRepository extends JpaRepository<ArticuloC, Long> {

    @Query(value = "SELECT * FROM articuloc a" +
            " JOIN articulop p ON a.rubroarticulo = p.rubroarticulo" +
            " WHERE p.fechadesde <= ?1" +
            " AND p.fechahasta >= ?1", nativeQuery = true)
    List<ArticuloC> findByFecha(String fecha);

    @Query(value = "SELECT * FROM articuloc a" +
            " JOIN articulop p ON a.rubroarticulo = p.rubroarticulo" +
            " WHERE p.fechadesde <= ?2" +
            " AND p.fechahasta >= ?2" +
            " AND a.rubroarticulo =?1", nativeQuery = true)
    ArticuloC findByRubroArticuloAndFecha(String formattedNumber, String fecha);

    ArticuloC findByRubroArticulo(String codigo);

}
