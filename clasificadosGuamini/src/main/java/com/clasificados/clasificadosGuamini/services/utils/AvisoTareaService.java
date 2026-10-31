package com.clasificados.clasificadosGuamini.services.utils;

import java.time.LocalDateTime;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clasificados.clasificadosGuamini.repositories.AvisoRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service  
@RequiredArgsConstructor
@Data 
public class AvisoTareaService {

    private final AvisoRepository avisoRepository;
    
    // Se ejecuta todos los días a las 00:00 horas 
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void expirarAvisosVencidos() {
        LocalDateTime ahora = LocalDateTime.now();
        
        int actualizados = avisoRepository.expirarAvisosVencidos(ahora);
        
        System.out.println("Se marcaron como inactivos " + actualizados + " avisos vencidos.");
    }
    
}
