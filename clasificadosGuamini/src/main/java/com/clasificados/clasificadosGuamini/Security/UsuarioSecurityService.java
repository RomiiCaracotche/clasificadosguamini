package com.clasificados.clasificadosGuamini.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import com.clasificados.clasificadosGuamini.repositories.UsuarioRepository;

@Service 
public class UsuarioSecurityService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioSecurityService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override 
    @Transactional(readOnly = true) 
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuarioExiste = usuarioRepository.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rol = usuarioExiste.isAdmin() ? "ROLE_ADMIN" : "ROLE_USER";
        
        //Creamos la lista de autoridades que Spring Security exige
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(rol));

        return new org.springframework.security.core.userdetails.User(usuarioExiste.getNombreUsuario(), usuarioExiste.getPassword(), usuarioExiste.isActivo(), true, true, true, authorities);
    }

}
