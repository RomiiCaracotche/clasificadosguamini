package com.clasificados.clasificadosGuamini.repositories;

import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InmuebleRepository extends JpaRepository<Inmueble,Long> {

    @Query("SELECT i FROM Inmueble i WHERE i.estado = :estado AND i.fechaExpiracion > :fecha")
    List<Inmueble> findAllActivos(@Param("estado") EstadoAviso estado, @Param("estado") LocalDateTime fecha);

}
