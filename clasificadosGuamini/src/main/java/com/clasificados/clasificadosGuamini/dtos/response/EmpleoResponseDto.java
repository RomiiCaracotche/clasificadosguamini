package com.clasificados.clasificadosGuamini.dtos.response;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.ModalidadEmpleo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EmpleoResponseDto extends AvisoResponseDto {

    private ModalidadEmpleo modalidadEmpleo;
    
    private String rubro; 

    private DisponibilidadHoraria disponibilidadHoraria; 
    
    private String requisitos;
    
}
