package com.clasificados.clasificadosGuamini.dtos.response;

import java.time.LocalDateTime;
import com.clasificados.clasificadosGuamini.entities.Aviso;
import com.clasificados.clasificadosGuamini.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReporteAvisoResponseDto {
    
    private Long id;

    private LocalDateTime fechaReporte;

    private String descripcion;

    private Aviso aviso;

    private Usuario usuario;

}
