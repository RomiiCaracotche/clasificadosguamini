package com.clasificados.clasificadosGuamini.controllers.Admin;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.dtos.response.AvisoResponseDto;
import com.clasificados.clasificadosGuamini.dtos.response.ReporteAvisoResponseDto;
import com.clasificados.clasificadosGuamini.services.Admin.AvisoAdminService;
import com.clasificados.clasificadosGuamini.services.ReporteAvisoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')") 
@RequestMapping("/api/admin/avisos")
public class AvisoAdminController {

    private final AvisoAdminService avisoAdminService;
    private final ReporteAvisoService reporteAvisoService;

    @GetMapping
    public ResponseEntity<List<AvisoResponseDto>> listarAvisos() {
        return ResponseEntity.ok().body(avisoAdminService.listarAvisos());
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<AvisoResponseDto>> listarPendientes() {
        return ResponseEntity.ok().body(avisoAdminService.listarPendientes());
    }

    @GetMapping("/revision")
    public ResponseEntity<List<AvisoResponseDto>> listarEnRevision() {
        return ResponseEntity.ok().body(avisoAdminService.listarEnRevision());
    }

    @GetMapping("/reportados")
    public ResponseEntity<List<ReporteAvisoResponseDto>> listarReportados() {
        return ResponseEntity.ok().body(reporteAvisoService.listarReportados());
    }

    @GetMapping("/rechazados")
    public ResponseEntity<List<AvisoResponseDto>> listarRechazados() {
        return ResponseEntity.ok().body(avisoAdminService.listarRechazados());
    }

    @PatchMapping("/aprobar/{id}")
    public ResponseEntity<Void> aprobarAviso(@PathVariable Long id) {
        avisoAdminService.aprobarAviso(id);
        return ResponseEntity.noContent().build(); 
    }

    @PatchMapping("/rechazar/{id}") 
    public ResponseEntity<Void> rechazarAviso(@PathVariable Long id) {
        avisoAdminService.rechazarAviso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cantidad")
    public ResponseEntity<Long> cantidadAvisos() {
        return ResponseEntity.ok().body(avisoAdminService.cantidadAvisos());
    }

    @DeleteMapping("/reportado/{id}")
    public ResponseEntity<String> eliminarReportado(@PathVariable Long id) {
        return ResponseEntity.ok(reporteAvisoService.eliminarReportado(id));
    }

}