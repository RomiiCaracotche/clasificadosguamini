package com.clasificados.clasificadosGuamini.controllers.Admin;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.services.Admin.UsuarioAdminService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/admin/usuarios")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class UsuarioAdminController {
    
    private final UsuarioAdminService usuarioAdminService;

    @PatchMapping("/rol/{id}")
    public ResponseEntity<Void> cambiarRolUsuario(@PathVariable("id") Long idUsuario) {
        usuarioAdminService.cambiarRolUsuario(idUsuario);
        return ResponseEntity.noContent().build(); 
    }

    @PatchMapping("/deshabilitar/{id}")
    public ResponseEntity<Void> deshabilitarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioAdminService.deshabilitarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/habilitar/{id}")
    public ResponseEntity<Void> habilitarUsuario(@PathVariable("id") Long idUsuario) {
        usuarioAdminService.habilitarUsuario(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/avisos/{id}")
    public ResponseEntity<List<AvisoResponseDto>> AvisosPorUsuario(@PathVariable("id") Long idUsuario) {
        return ResponseEntity.ok().body(usuarioAdminService.AvisosPorUsuario(idUsuario));
    }
    
    @GetMapping("/cantidad")
    public ResponseEntity<Long> cantidadUsuarios() {
       Long total = usuarioAdminService.cantidadUsuarios();
        return ResponseEntity.ok().body(total);
    }

}
