package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Delegacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DelegacionRepository extends JpaRepository<Delegacion, Long> {

    Delegacion findByCodigo(String codigo);

}
