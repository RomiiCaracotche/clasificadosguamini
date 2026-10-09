package com.clasificados.clasificadosGuamini.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import org.springframework.stereotype.Repository;

@Repository 
public interface CompraVentaRepository extends JpaRepository<CompraVenta,Long>{

    /* List<CompraVenta> findAll();
    Optional<CompraVenta> findById(Long id);

    CompraVenta save(CompraVenta compraVenta);

    void delete(CompraVenta compraVenta); */

}
