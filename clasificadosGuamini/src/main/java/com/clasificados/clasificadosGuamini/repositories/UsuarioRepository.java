package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clasificados.clasificadosGuamini.entities.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByDni(String dni);  
    boolean existsByEmail(String email);
    boolean existsByCelular(String celular);

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.favoritos WHERE u.id = :id")
    Optional<Usuario> findByIdWithFavoritos(@Param("id") Long id);

} 
