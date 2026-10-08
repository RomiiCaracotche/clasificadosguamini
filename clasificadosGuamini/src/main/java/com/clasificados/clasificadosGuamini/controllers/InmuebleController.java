package com.clasificados.clasificadosGuamini.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.clasificados.clasificadosGuamini.entities.Inmueble;
import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.services.InmuebleService;
import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/inmuebles")
public class InmuebleController {

    private InmuebleService inmuebleService;

    public InmuebleController(InmuebleService inmuebleService) {
        this.inmuebleService = inmuebleService;
    }

    @GetMapping 
    public ResponseEntity<List<Inmueble>> listarInmuebles() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inmueble> listarInmueblePorId(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Inmueble>> buscarFiltrosAvanzados(@RequestParam(required = false) String localidad, @RequestParam(required = false) Double precio, @RequestParam(required = false) TipoOperacion tipoOperacion) {
                return null;
            }

    @PostMapping 
    public ResponseEntity<Inmueble> crearInmueble(@Valid @RequestBody Inmueble inmueble){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inmueble> modificarInmueble(@PathVariable Long id, @Valid @RequestBody Inmueble inmueble) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInmueblePorId(@PathVariable Long id) {
        return null;
    }
}
