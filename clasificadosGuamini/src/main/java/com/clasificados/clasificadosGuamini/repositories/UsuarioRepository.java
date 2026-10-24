package com.clasificados.clasificadosGuamini.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    boolean existsByNombreUsuario(String nombreUsuario);
    boolean existsByDni(String dni);  
    boolean existsByEmail(String email);
    boolean existsByCelular(String celular);

} 
