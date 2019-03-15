package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.ArticuloC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloCRepository extends JpaRepository<ArticuloC, Long> {

    @Query("select c from ArticuloC c" +
            " join c.articuloPList p" +
            " where p.fechaDesde <= ?1" +
            " and p.fechaHasta >= ?1")
    List<ArticuloC> findByFecha(String fecha);

    @Query("select c from ArticuloC c" +
            " join c.articuloPList p" +
            " where c.rubroArticulo = ?1" +
            " and p.fechaDesde <= ?2" +
            " and p.fechaHasta >= ?2")
    ArticuloC findByNumeroAndFecha(String id, String fecha);

    ArticuloC findByRubroArticulo(String codigo);
}
