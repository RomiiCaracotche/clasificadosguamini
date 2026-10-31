package com.clasificados.clasificadosGuamini.repositories;

import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvisoRepository extends JpaRepository<Aviso,Long> {

    List<Aviso> findByEstado(EstadoAviso estado);
    List<Aviso> findByTituloContainingIgnoreCase(String palabraClave);
    @Modifying
    @Query("UPDATE Aviso a SET a.estado = 'INACTIVO' WHERE a.fechaExpiracion < :ahora AND a.estado <> 'INACTIVO'")
    int expirarAvisosVencidos(@Param("ahora") LocalDateTime ahora);

}
