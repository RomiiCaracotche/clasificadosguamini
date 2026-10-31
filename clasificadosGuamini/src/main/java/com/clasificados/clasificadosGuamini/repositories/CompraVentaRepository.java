package com.clasificados.clasificadosGuamini.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clasificados.clasificadosGuamini.entities.CompraVenta;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;

public interface CompraVentaRepository extends JpaRepository<CompraVenta,Long>{

    @Query("SELECT c FROM CompraVenta c WHERE c.estado = :estado AND c.fechaExpiracion > :fecha")
    List<CompraVenta> findAllActivos(@Param("estado") EstadoAviso estado, @Param("estado") LocalDateTime fecha);

}
