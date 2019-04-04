package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.ArticuloC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloCRepository extends JpaRepository<ArticuloC, Long> {

    List<ArticuloC> findByFecha(String fecha);

    ArticuloC findByRubroArticuloAndFecha(String formattedNumber, String fecha);

    ArticuloC findByRubroArticulo(String codigo);

}
