package com.clasificados.clasificadosGuamini.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.ReporteAviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;

public interface ReporteAvisoRepository extends JpaRepository<ReporteAviso,Long> {

    Long countByAvisoId(Long id);
    
    @Modifying
    @Query("DELETE FROM ReporteAviso r WHERE r.aviso.id = :avisoId")
    void deleteByAvisoId(@Param("avisoId") Long avisoId);

    boolean existsByUsuarioAndAviso(Usuario usuario, Aviso aviso);

    @Query("SELECT r FROM ReporteAviso r " + 
            "JOIN FETCH r.aviso a " +  
            "JOIN FETCH a.anunciante " +      
            "JOIN FETCH r.usuario u " +   
            "WHERE a.estado = :estado")
    List<ReporteAviso> findAllByEstadoAviso(@Param("estado") EstadoAviso estado);
    
}
