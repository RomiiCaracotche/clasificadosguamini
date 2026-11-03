package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByDni(String dni);  
    boolean existsByEmail(String email);
    boolean existsByCelular(String celular);
    boolean existsByNombreUsuarioAndIdNot(String nombreUsuario, Long id);
    boolean existsByDniAndIdNot(String dni, Long id);
    boolean existsByCelularAndIdNot(String celular, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.favoritos WHERE u.nombreUsuario = :nombreUsuario")
    Optional<Usuario> findByNombreUsuarioWithFavoritos(@Param("nombreUsuario") String nombreUsuario);

} 
