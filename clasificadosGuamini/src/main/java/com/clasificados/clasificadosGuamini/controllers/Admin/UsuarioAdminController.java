package com.clasificados.clasificadosGuamini.controllers.Admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.services.Admin.UsuarioAdminService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/admin/usuarios")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UsuarioAdminController {
    
    private UsuarioAdminService usuarioAdminService;

    @PatchMapping("/rol")
    public ResponseEntity<Void> cambiarRolUsuario(Long id) {
        usuarioAdminService.cambiarRolUsuario(id);
        return ResponseEntity.noContent().build(); 
    }

    @PatchMapping("/deshabilitar")
    public ResponseEntity<Void> deshabilitarUsuario(Long id) {
        usuarioAdminService.deshabilitarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/habilitar")
    public ResponseEntity<Void> habilitarUsuario(Long id) {
        usuarioAdminService.habilitarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/cantidad")
    public ResponseEntity<Long> cantidadUsuarios() {
       Long total = usuarioAdminService.cantidadUsuarios();
        return ResponseEntity.ok().body(total);
    }

}
