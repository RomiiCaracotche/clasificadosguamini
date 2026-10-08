package com.clasificados.clasificadosGuamini.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clasificados.clasificadosGuamini.services.CompraVentaService;

@Controller 
@RequestMapping("/api/compraventa")
public class CompraVentaController {

    private final CompraVentaService compraVentaService;

    public CompraVentaController(CompraVentaService compraVentaService){
        this.compraVentaService = compraVentaService;
    }
    
}
