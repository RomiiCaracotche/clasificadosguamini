package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository 
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    
} 
