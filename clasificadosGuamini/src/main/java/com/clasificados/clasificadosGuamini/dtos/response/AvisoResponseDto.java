package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.enums.EstadoAviso;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
public class AvisoResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fecha = LocalDateTime.now();
    private String localidad;
    private EstadoAviso estado;
    private Long idAnunciante;
    
}
