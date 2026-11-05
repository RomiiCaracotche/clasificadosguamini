package com.clasificados.clasificadosGuamini.dtos.request.Empleo;

import com.clasificados.clasificadosGuamini.enums.DisponibilidadHoraria;
import com.clasificados.clasificadosGuamini.enums.ModalidadEmpleo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class EmpleoActualizadoRequestDto {
    private String titulo;
    private String descripcion;
    private String localidad;

    private ModalidadEmpleo modalidadEmpleo;
    private String rubro; 
    private DisponibilidadHoraria disponibilidadHoraria; 
    private String requisitos;
}
