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
import com.clasificados.clasificadosGuamini.services.Admin.AvisoAdminService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/avisos")
@PreAuthorize("hasRole('ADMIN')") 
@RequiredArgsConstructor
public class AvisoAdminController {

    private AvisoAdminService avisoAdminService;

    @GetMapping("/pendientes")
    public ResponseEntity<List<AvisoResponseDto>> listarPendientes() {
        return ResponseEntity.ok().body(avisoAdminService.listarPendientes());
    }

    @PatchMapping("/{id}/aprobar")
    public ResponseEntity<Void> aprobarAviso(@PathVariable Long id) {
        avisoAdminService.aprobarAviso(id);
        return ResponseEntity.noContent().build(); 
    }

    @PatchMapping("/{id}/rechazar") 
    public ResponseEntity<Void> rechazarAviso(@PathVariable Long id) {
        avisoAdminService.rechazarAviso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Long> cantidadAvisos() {
        Long total = avisoAdminService.cantidadAvisos();
        return ResponseEntity.ok().body(total);
    }

}