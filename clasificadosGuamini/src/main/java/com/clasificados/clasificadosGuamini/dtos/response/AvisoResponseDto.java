package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AvisoResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();
    private String localidad;
    private EstadoAviso estado;
    private Long idAnunciante;
    
}
