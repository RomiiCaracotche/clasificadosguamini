package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import org.springframework.stereotype.Repository;

@Repository 
public interface CompraVentaRepository extends JpaRepository<CompraVenta,Long>{

}
