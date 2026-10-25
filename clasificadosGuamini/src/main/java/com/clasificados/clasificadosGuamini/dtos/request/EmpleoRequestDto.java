package com.clasificados.clasificadosGuamini.dtos.request;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.TipoEmpleo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data 
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class EmpleoRequestDto extends AvisoRequestDto {

    private TipoEmpleo tipoEmpleo;
    
    private String rubro; 

    private DisponibilidadHoraria disponibilidadHoraria; 
    
    private String requisitos;

}
