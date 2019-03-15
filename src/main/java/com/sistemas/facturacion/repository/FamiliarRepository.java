package com.sistemas.facturacion.repository;

import com.sistemas.facturacion.model.Familiar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamiliarRepository extends JpaRepository<Familiar, Integer> {

    @Query("select f from Familiar f" +
            " inner join f.titular t" +
            " inner join t.titularAList ta" +
            " where ta.delegacion = ?1" +
            " and f.fechaBaja = '29991231")
    List<Familiar> findBySindicato(String sindicato);

    @Query("select f from Familiar f" +
            " where f.fechaBaja = '29991231'")
    List<Familiar> findAllActives();

    Familiar findByOrden(String afiliado);
}
