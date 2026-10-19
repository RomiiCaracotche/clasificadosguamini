package com.clasificados.clasificadosGuamini.repositories;

import com.clasificados.clasificadosGuamini.entities.Rol;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol,Long>{
    
    Optional<Rol> findByNombre(String name);
}
