package com.clasificados.clasificadosGuamini.dtos.response;

import com.clasificados.clasificadosGuamini.enums.TipoOperacion;
import com.clasificados.clasificadosGuamini.enums.TipoVivienda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InmuebleResponseDto extends AvisoResponseDto {

    private TipoOperacion tipoOperacion;

    private TipoVivienda tipoVivienda;
    
    private Double precio;

    private Integer ambientes;

    private Integer habitaciones;

    private Boolean patio;

    private Integer banos;

    private Boolean mascotas;

    private Double expensas;

    private String direccion;

}
