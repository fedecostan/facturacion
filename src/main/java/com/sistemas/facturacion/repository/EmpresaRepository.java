package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Empresa;
import com.sistemas.facturacion.model.TipoDocumento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}