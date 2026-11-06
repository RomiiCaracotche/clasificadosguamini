package com.clasificados.clasificadosGuamini.repositories;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clasificados.clasificadosGuamini.entities.Empleo;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;

public interface EmpleoRepository extends JpaRepository<Empleo,Long> {

    @Query("SELECT e FROM Empleo e WHERE e.estado = :estado AND e.fechaExpiracion > :fecha")
    List<Empleo> findAllActivos(@Param("estado") EstadoAviso estado, @Param("fecha") LocalDateTime fecha);

}
