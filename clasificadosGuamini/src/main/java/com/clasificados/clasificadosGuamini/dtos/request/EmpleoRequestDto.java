package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmpleoRequestDto extends AvisoRequestDto {

    @NotNull(message = "El tipo de empleo es obligatorio")
    private TipoEmpleo tipoEmpleo;
    
    @NotNull(message = "El rubro es obligatorio")
    private String rubro; 

    @NotNull(message = "La disponibilidad horaria es obligatoria")
    private DisponibilidadHoraria disponibilidadHoraria; 
    
    @NotNull(message = "Los requisitos son obligfatorios")
    private String requisitos;

}
