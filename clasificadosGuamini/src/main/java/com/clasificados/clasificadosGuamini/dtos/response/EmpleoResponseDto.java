package com.clasificados.clasificadosGuamini.dtos.response;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmpleoResponseDto extends AvisoResponseDto {

    @NotNull(message = "Este campo es obligatorio")
    private TipoEmpleo tipoEmpleo;
    
    @NotBlank(message = "Este campo es obligatorio")
    private String rubro; 

    @NotNull(message = "Este campo es obligatorio")
    private DisponibilidadHoraria disponibilidadHoraria; 
    
    private String requisitos;
    
}
