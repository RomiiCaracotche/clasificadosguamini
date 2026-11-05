package com.clasificados.clasificadosGuamini.dtos.request.Aviso;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class ReporteAvisoRequestDto {
    
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

}
