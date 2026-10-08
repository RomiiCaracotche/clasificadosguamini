package com.clasificados.clasificadosGuamini.services;

import org.springframework.stereotype.Service;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;

@Service 
public class AvisoService {

    private final AvisoRepository avisoRepository;

    public AvisoService(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    
}
